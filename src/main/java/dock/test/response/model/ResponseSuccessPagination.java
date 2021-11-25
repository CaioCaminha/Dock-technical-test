package dock.test.response.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResponseSuccessPagination {
    private MetaPagination metaPagination;
    private Object data;
}
