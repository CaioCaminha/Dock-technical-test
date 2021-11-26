package product.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import product.controller.v1.ProductController;
import product.dto.ProductDto;
import product.service.ProductService;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    private final String BODY_JSON = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    ProductDto productDto = ProductDto.generateDto(this.BODY_JSON);

    @Mock
    private ProductService productService;

    public ProductControllerTest() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProduct() throws Exception {


        ProductController productController = new ProductController(this.productService);

        when(this.productService.createProduct(anyString())).thenReturn(ResponseEntity.created(new URI("")).body(productDto));

        ResponseEntity response = productController.createProduct(this.BODY_JSON);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(ProductDto.class, response.getBody());

    }

    @Test
    public void updateProject() throws Exception {

        ProductController productController = new ProductController(this.productService);

        when(this.productService.updateProduct(anyInt(),any())).thenReturn(ResponseEntity.ok().body(productDto));

        ResponseEntity responseEntity = productController.updateProduct(productDto.getLogic(), productDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertInstanceOf(ProductDto.class, responseEntity.getBody());
    }


    @Test
    public void getProject() throws Exception {

        ProductController productController = new ProductController(this.productService);
        when(this.productService.getProduct(anyInt())).thenReturn(ResponseEntity.ok().body(productDto));

        ResponseEntity responseEntity = productController.getProduct(productDto.getLogic());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertInstanceOf(ProductDto.class, responseEntity.getBody());
    }

    @Test
    public void getAllProjects() throws Exception {

        ProductController productController = new ProductController(this.productService);

        when(this.productService.getAllProducts(any())).thenReturn(ResponseEntity.ok().build());

        ResponseEntity responseEntity = productController.getAllProducts(Pageable.ofSize(10));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
