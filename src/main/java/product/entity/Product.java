package product.entity;

import product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Productz")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private Integer logic;
    private String serial;
    private String model;
    private Integer sam;
    private String ptid;
    private Integer plat;
    private String version;
    private Integer mxr;
    private Integer mxf;
    private String PVERFM;

    public Product(ProductDto productDto){
        this.logic = productDto.getLogic();
        this.serial = productDto.getSerial();
        this.model = productDto.getModel();
        this.sam = productDto.getSam();
        this.ptid = productDto.getPtid();
        this.plat = productDto.getPlat();
        this.version = productDto.getVersion();
        this.mxr = productDto.getMxr();
        this.mxf = productDto.getMxf();
        this.PVERFM = productDto.getPVERFM();
    }
}
