package top.ysxc.zfile.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.model.enums.StorageTypeEnumJsonDeSerializerConvert;

/**
 * @author ysxc
 * @create 2021-09-07 10:30 下午
 */
@Data
public class DriveConfigDTO {

    private Integer id;

    private String name;

    @JsonDeserialize(using = StorageTypeEnumJsonDeSerializerConvert.class)
    private StorageTypeEnum type;

    private Boolean enable;

    private boolean enableCache;

    private boolean autoRefreshCache;

    private boolean searchEnable;

    private boolean searchIgnoreCase;

    private boolean searchContainEncryptedFile;

    private Integer orderNum;

    private StorageStrategyConfig storageStrategyConfig;

    private boolean defaultSwitchToImgMode;
}
