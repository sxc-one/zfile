package top.ysxc.zfile.model.dto;

import lombok.Data;

/**
 * @author ysxc
 * @create 2021-09-07 10:30 下午
 */
@Data
public class DriveConfigDTO {

    private Integer id;

    private String name;

    private StorageStrategyConfig storageStrategyConfig;
}
