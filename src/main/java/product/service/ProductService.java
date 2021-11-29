package product.service;

import product.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ProductService {

    public ResponseEntity createProduct(String dock);

    public ResponseEntity getProduct(Integer logic);

    public ResponseEntity updateProduct(Integer logic, ProductDto productDto);
}
