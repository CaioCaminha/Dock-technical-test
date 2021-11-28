package product.controller.v1;


import product.dto.ProductDto;
import product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/dock", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;


    @PostMapping(consumes = "text/html;charset=utf-8")
    public ResponseEntity createProduct(@RequestBody String product){
        return productService.createProduct(product);
    }

    @PutMapping(value = "/{logic}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("logic") Integer logic,
                                                    @RequestBody ProductDto productDto){
        return this.productService.updateProduct(logic, productDto);
    }

    @GetMapping("/{logic}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("logic") Integer logic){
        return this.productService.getProduct(logic);
    }

    @GetMapping
    public ResponseEntity<ProductDto> getAllProducts(@PageableDefault(sort = "logic",
                                                            direction = Sort.Direction.ASC,
                                                            page = 0, size = 10) Pageable pageable){
        return this.productService.getAllProducts(pageable);
    }



}
