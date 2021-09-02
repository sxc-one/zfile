package top.ysxc.zfile.model.enums;

/**
 * @author ysxc
 * @create 2021-09-02 10:03 上午
 */
public enum  FileTypeEnum {

    FILE("File"),

    FOLDER("Folder");

    private String value;

    FileTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
