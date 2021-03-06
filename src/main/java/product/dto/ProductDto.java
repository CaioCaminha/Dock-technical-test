package product.dto;

import product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NonNull
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


    public ProductDto(Product dock){
        this.logic = dock.getLogic();
        this.serial = dock.getSerial();
        this.model = dock.getModel();
        this.sam = dock.getSam();
        this.ptid = dock.getPtid();
        this.plat = dock.getPlat();
        this.version = dock.getVersion();
        this.mxr = dock.getMxr();
        this.mxf = dock.getMxf();
        this.PVERFM = dock.getPVERFM();
    }

    public static ProductDto generateDto(String dock) throws Exception {
        String[] properties = dock.split(";");
        if(!(properties.length == 10)){
            throw new Exception("Some property is missing in json");
        }
        return new ProductDto(Integer.parseInt(properties[0]),
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
