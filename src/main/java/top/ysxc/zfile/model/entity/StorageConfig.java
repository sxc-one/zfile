package top.ysxc.zfile.model.entity;

import lombok.Data;
import top.ysxc.zfile.model.enums.StorageTypeEnum;

import javax.persistence.*;

/**
 * @author ysxc
 * @create 2021-09-07 10:49 下午
 */
@Entity(name = "STORAGE_CONFIG")
@Data
public class StorageConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private StorageTypeEnum type;

    @Column(name = "k")
    private String key;

    private String title;

    @Lob
    private String value;

    private Integer driveId;

    public StorageConfig(String key, String title) {
        this.key = key;
        this.title = title;
    }

    public StorageConfig() {

    }
}
