package top.ysxc.zfile.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author ysxc
 * @create 2021-09-13 3:58 下午
 */
@Converter(autoApply = true)
public class StorageTypeEnumConvert implements AttributeConverter<StorageTypeEnum, String> {
    @Override
    public String convertToDatabaseColumn(StorageTypeEnum attribute) {
        return attribute.getKey();
    }

    @Override
    public StorageTypeEnum convertToEntityAttribute(String dbData) {
        return StorageTypeEnum.getEnum(dbData);
    }
}
