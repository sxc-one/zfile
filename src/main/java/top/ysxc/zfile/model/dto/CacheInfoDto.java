package top.ysxc.zfile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author ysxc
 * @create 2021-09-02 9:46 上午
 */
@Data
@AllArgsConstructor
public class CacheInfoDto {

    private Integer cacheCount;

    private Integer hitCount;

    private Integer missCount;

    private Set<String> cacheKeys;
}
