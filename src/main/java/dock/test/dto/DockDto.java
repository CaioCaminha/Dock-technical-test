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
    private String ptid;
    private Integer plat;
    @NonNull
    @NotEmpty
    private String version;
    private Integer mxr;
    private Integer mxf;
    private String PVERFM;


    public static DockDto generateDto(String dock) {
        String[] properties = dock.split(";");
        return new DockDto(Integer.parseInt(properties[0]),
                properties[1],
                properties[2],
                Integer.parseInt(properties[3]),
                properties[4],
                Integer.parseInt(properties[5]),
                properties[6],
                Integer.parseInt(properties[7]),
                Integer.parseInt(properties[8]),
                properties[9]);

    }
}
