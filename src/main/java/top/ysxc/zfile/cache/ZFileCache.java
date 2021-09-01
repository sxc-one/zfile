package top.ysxc.zfile.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.ysxc.zfile.model.dto.SystemConfigDTO;

/**
 * @author ysxc
 * @create 2021-09-01 4:28 下午
 */
@Component
@Slf4j
public class ZFileCache {

    private SystemConfigDTO systemConfigCache;

    public void updateConfig(SystemConfigDTO systemConfigCache) {
        this.systemConfigCache = systemConfigCache;
    }

    public SystemConfigDTO getConfig() {
        return this.systemConfigCache;
    }

    public void removeConfig() {
        this.systemConfigCache = null;
    }
}
