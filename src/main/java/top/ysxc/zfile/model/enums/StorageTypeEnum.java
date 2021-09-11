package top.ysxc.zfile.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ysxc
 * @create 2021-09-08 10:07 上午
 */
public enum StorageTypeEnum {

    LOCAL("local", "本地存储"),
    FTP("ftp", "FTP");

    private String key;
    private String description;

    private static Map<String, StorageTypeEnum> enumMap = new HashMap<>();

    static {
        for (StorageTypeEnum type : StorageTypeEnum.values()) {
            enumMap.put(type.getKey(), type);
        }
    }

    StorageTypeEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static StorageTypeEnum getEnum(String value) {
        return enumMap.get(value.toLowerCase());
    }
}
