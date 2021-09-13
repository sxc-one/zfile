package top.ysxc.zfile.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.model.enums.StorageTypeEnumSerializerConvert;

/**
 * 系统设置传输类
 * @author ysxc
 * @create 2021-09-01 4:26 下午
 */
@ToString
@Data
public class SystemConfigDTO {

    @JsonIgnore
    private Integer id;

    private String siteName;

    private String username;

    @JsonSerialize(using = StorageTypeEnumSerializerConvert.class)
    private StorageTypeEnum storageStrategy;

    @JsonIgnore
    private String password;

    private String domain;

    private String customJs;

    private String customCss;

    private String tableSize;

    private Boolean showOperator;

    private Boolean showDocument;

    private String announcement;

    private Boolean showAnnouncement;

    private String layout;

    private Boolean showLinkBtn;

    private Boolean showShortLink;

    private Boolean showPathLink;
}
