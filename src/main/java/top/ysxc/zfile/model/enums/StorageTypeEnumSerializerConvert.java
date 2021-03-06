package top.ysxc.zfile.model.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author ysxc
 * @create 2021-09-13 3:54 下午
 */
public class StorageTypeEnumSerializerConvert extends JsonSerializer<StorageTypeEnum> {
    @Override
    public void serialize(StorageTypeEnum storageTypeEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(storageTypeEnum.getKey());
    }
}
