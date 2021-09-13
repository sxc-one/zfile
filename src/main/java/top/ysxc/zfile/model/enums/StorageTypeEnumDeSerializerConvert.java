package top.ysxc.zfile.model.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/**
 * @author ysxc
 * @create 2021-09-13 11:28 上午
 */
public class StorageTypeEnumDeSerializerConvert implements Converter<String, StorageTypeEnum> {
    @Override
    public StorageTypeEnum convert(@NonNull String s) {
        return StorageTypeEnum.getEnum(s);
    }
}
