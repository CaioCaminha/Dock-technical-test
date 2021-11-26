package product.service.impl;


import lombok.AllArgsConstructor;
import product.json.JsonSchemaValidator;
import product.response.model.Error;
import product.service.ProductService;
import product.dto.ProductDto;
import product.entity.Product;
import product.repository.ProductRepository;
import product.response.ResponseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ResponseService response;

    private final JsonSchemaValidator jsonSchemaValidator;


    @Override
    public ResponseEntity createProduct(String dock) {
        try{
            ProductDto productDto = ProductDto.generateDto(dock);
            List validations = this.jsonSchemaValidator.validateJson(productDto);
            if(validations.size() == 0){
                Product product = this.productRepository.save(new Product(productDto));
                return response.success(productDto, HttpStatus.CREATED);
            }else{
                return response.jsonValidationError(validations, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            if(e.getLocalizedMessage().equals("Some property is missing in json")){
                return response.error(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
            }else{
                return response.error(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public ResponseEntity getProduct(Integer logic) {
        try{
            Optional<Product> optionalDockEntity = this.productRepository.findById(logic);
            if(optionalDockEntity.isPresent()){
                return response.success(optionalDockEntity.get(), HttpStatus.OK);
            }else{
                return response.error("Product not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return response.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getAllProducts(Pageable pageable) {
        try{
            return response.success(this.productRepository.findAll(pageable), HttpStatus.OK);
        }catch (Exception e){
            return response.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateProduct(Integer logic, ProductDto productDto) {
        try{
            List validations = this.jsonSchemaValidator.validateJson(productDto);
            if(validations.size() == 0){
                Optional<Product> optionalDockEntity = this.productRepository.findById(logic);
                if(optionalDockEntity.isPresent()){
                    Product product = this.update(productDto, optionalDockEntity.get());
                    return response.success(this.productRepository.save(product), HttpStatus.OK);
                }
                return response.error("Product not found", HttpStatus.BAD_REQUEST);
            }
            return response.jsonValidationError(validations, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return response.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Product update(ProductDto productDto, Product product){
            product.setSerial(productDto.getSerial());
            product.setModel(productDto.getModel());
            product.setSam(productDto.getSam());
            product.setPtid(productDto.getPtid());
            product.setPlat(productDto.getPlat());
            product.setVersion(productDto.getVersion());
            product.setMxr(productDto.getMxr());
            product.setMxf(productDto.getMxf());
            product.setPVERFM(productDto.getPVERFM());
            return product;
    }

}
