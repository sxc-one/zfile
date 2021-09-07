package top.ysxc.zfile.model.entity;

import lombok.Data;

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

    @Column(name = "k")
    private String key;

    private String value;

    private Integer driveId;
}
