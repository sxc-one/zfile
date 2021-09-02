package top.ysxc.zfile.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ysxc
 * @create 2021-09-02 9:59 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveCacheKey {

    private Integer driveId;

    private String key;
}
