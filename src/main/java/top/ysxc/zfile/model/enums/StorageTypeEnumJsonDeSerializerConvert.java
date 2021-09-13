package top.ysxc.zfile.model.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author ysxc
 * @create 2021-09-13 3:51 下午
 */
public class StorageTypeEnumJsonDeSerializerConvert extends JsonDeserializer<StorageTypeEnum> {
    @Override
    public StorageTypeEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return StorageTypeEnum.getEnum(jsonParser.getText());
    }
}
