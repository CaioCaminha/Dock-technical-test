package dock.entity;

import dock.dto.DockDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DockEntity")
@NoArgsConstructor
@AllArgsConstructor
public class DockEntity {

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

    public DockEntity(DockDto dockDto){
        this.logic = dockDto.getLogic();
        this.serial = dockDto.getSerial();
        this.model = dockDto.getModel();
        this.sam = dockDto.getSam();
        this.ptid = dockDto.getPtid();
        this.plat = dockDto.getPlat();
        this.version = dockDto.getVersion();
        this.mxr = dockDto.getMxr();
        this.mxf = dockDto.getMxf();
        this.PVERFM = dockDto.getPVERFM();
    }
}
