package dock.response.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ResponseError {
    private final String userMessage;
    private final String developerMessage;
    private final int code;
    private final String status;
}
