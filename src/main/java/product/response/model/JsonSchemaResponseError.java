package product.response.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class JsonSchemaResponseError {
    private final int code;
    private final String status;
    private final List<Object> errors;
}
