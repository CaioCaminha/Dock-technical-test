package product.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResponseService {

    ResponseEntity<Object> jsonValidationError(List<Object> errors, HttpStatus status);

    ResponseEntity<Object> error(String error, HttpStatus status);

    ResponseEntity<Object> success(Object data, HttpStatus status);

}
