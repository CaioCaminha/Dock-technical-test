package product.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import product.dto.ProductDto;
import product.entity.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTest {


    private final String BODY_JSON = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    ProductDto productDto = ProductDto.generateDto(this.BODY_JSON);

    @Autowired
    private ProductRepository productRepository;

    public ProductRepositoryTest() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByLogic() throws Exception {
        productRepository.save(new Product(productDto));
        Optional<Product> product = productRepository.findByLogic(productDto.getLogic());
        productRepository.delete(product.get());

        assertEquals(product.get(), new Product(productDto));
        assertTrue(product.isPresent());

    }

}
