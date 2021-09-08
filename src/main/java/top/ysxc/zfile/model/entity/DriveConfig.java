package top.ysxc.zfile.model.entity;

import lombok.Data;
import top.ysxc.zfile.model.enums.StorageTypeEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 驱动器
 * @author ysxc
 * @create 2021-09-01 7:33 下午
 */
@Entity(name = "DRIVER_CONFIG")
@Data
public class DriveConfig {

    @Id
    private Integer id;

    private Boolean enable;

    private String name;

    private Boolean enableCache;

    private Boolean autoRefreshCache;

    private StorageTypeEnum type;

    private Boolean searchEnable;

    private Boolean searchIgnoreCase;

    private Boolean searchContainEncryptedFile;

    private Integer orderNum;

    private Boolean defaultSwitchToImgMode;

}
