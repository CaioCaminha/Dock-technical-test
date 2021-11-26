package product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import product.dto.ProductDto;
import product.entity.Product;
import product.json.JsonSchemaValidator;
import product.repository.ProductRepository;
import product.response.ResponseService;
import product.response.model.MetaPagination;
import product.service.impl.ProductServiceImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    private final String BODY_JSON = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    @Value("${api.name}")
    private String server;

    ProductDto productDto = ProductDto.generateDto(this.BODY_JSON);

    MetaPagination responseSuccess = MetaPagination.builder()
            .server(server)
            .count(10L)
            .limit(5)
            .page(1)
            .pageCount(2)
            .build();

    @Mock
    private ProductRepository productRepository;

    @Mock
    private JsonSchemaValidator jsonSchemaValidator;

    @Mock
    private ResponseService responseService;

    public ProductServiceTest() throws Exception {
    }

    @BeforeEach
    public void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    public void createProduct() throws Exception {

        when(this.productRepository.save(any())).thenReturn(new Product(productDto));

        when(this.responseService.success(any(), any())).thenReturn(ResponseEntity.created(new URI("")).body(productDto));

        when(this.jsonSchemaValidator.validateJson(any())).thenReturn(new ArrayList());

        ProductServiceImpl productService = new ProductServiceImpl(this.productRepository, this.responseService, this.jsonSchemaValidator);

        ResponseEntity response = productService.createProduct(this.BODY_JSON);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(ProductDto.class, response.getBody());

    }

    @Test
    public void updateProject() throws Exception {

        when(this.productRepository.findById(anyInt())).thenReturn(Optional.of(new Product(productDto)));

        when(this.responseService.success(any(), any())).thenReturn(ResponseEntity.ok().body(productDto));

        when(this.jsonSchemaValidator.validateJson(any())).thenReturn(new ArrayList());

        ProductServiceImpl productService = new ProductServiceImpl(this.productRepository, this.responseService, this.jsonSchemaValidator);

        ResponseEntity response = productService.updateProduct(productDto.getLogic(), productDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(ProductDto.class, response.getBody());
    }


    @Test
    public void getProject() {

        when(this.productRepository.findById(anyInt())).thenReturn(Optional.of(new Product(productDto)));

        when(this.responseService.success(any(), any())).thenReturn(ResponseEntity.ok().body(productDto));

        ProductServiceImpl productService = new ProductServiceImpl(this.productRepository, this.responseService, this.jsonSchemaValidator);

        ResponseEntity response = productService.getProduct(productDto.getLogic());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(ProductDto.class, response.getBody());
    }

    @Test
    public void getAllProjects() throws Exception {
        List products = new ArrayList<>();
        products.add(new Product(productDto));
        when(this.productRepository.findAll()).thenReturn(products);

        when(this.responseService.success(any(), any())).thenReturn(ResponseEntity.ok().body(responseSuccess));

        ProductServiceImpl productService = new ProductServiceImpl(this.productRepository, this.responseService, this.jsonSchemaValidator);

        ResponseEntity response = productService.createProduct(this.BODY_JSON);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(MetaPagination.class, response.getBody());
    }
}
