package dock.test.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer logic;
    private String serial;
    private String model;
    private Integer sam;
    private Integer ptid;
    private Integer plat;
    private String version;
    private Integer mxr;
    private String PVERFM;
}
