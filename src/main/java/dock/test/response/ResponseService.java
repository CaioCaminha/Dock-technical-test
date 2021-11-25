package dock.test.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseService {

    ResponseEntity<Object> error(String userMessage, String developerMessage, HttpStatus status);

    ResponseEntity<Object> success(Object data, HttpStatus status);

}
