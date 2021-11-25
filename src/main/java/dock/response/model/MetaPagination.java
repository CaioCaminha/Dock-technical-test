package dock.response.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaPagination {;

	private String server;
	private Long count;
	private Integer limit;
	private Integer page;
	private Integer pageCount;
}