package top.ysxc.zfile.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ysxc
 * @create 2021-09-01 7:33 下午
 */
@Entity(name = "DRIVER_CONFIG")
@Data
public class DriveConfig {

    @Id
    private Integer id;

    private Boolean enableCache;
}
