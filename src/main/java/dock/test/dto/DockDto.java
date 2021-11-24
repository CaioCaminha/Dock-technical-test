package dock.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DockDto {

    @NonNull
    @NotEmpty
    private Integer logic;
    @NonNull
    @NotEmpty
    private String serial;
    @NonNull
    @NotEmpty
    private String model;
    @Min(0)
    private Integer sam;
    private Integer ptid;
    private Integer plat;
    @NonNull
    @NotEmpty
    private String version;
    private Integer mxr;
    private String PVERFM;
}
