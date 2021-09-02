package top.ysxc.zfile.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ysxc.zfile.cache.ZFileCache;
import top.ysxc.zfile.model.dto.CacheInfoDto;
import top.ysxc.zfile.model.entity.DriveConfig;
import top.ysxc.zfile.repository.DriverConfigRepository;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author ysxc
 * @create 2021-09-01 7:31 下午
 */
@Slf4j
@Service
public class DriveConfigService {

    @Resource
    private DriverConfigRepository driverConfigRepository;

    @Resource
    private ZFileCache zFileCache;

    /**
     * 获取指定驱动器设置
     *
     * @param   id
     *          驱动器 ID
     *
     * @return  驱动器设置
     */
    public DriveConfig findById(Integer id) {
        return driverConfigRepository.findById(id).orElse(null);
    }

    /**
     * 更新指定驱动器的缓存启用状态
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @param   cacheEnable
     *          是否启用缓存
     */
    public void updateCacheStatus(Integer driveId, Boolean cacheEnable) {
        DriveConfig driveConfig = findById(driveId);
        if (driveConfig != null) {
            driveConfig.setEnableCache(cacheEnable);
            driverConfigRepository.save(driveConfig);
        }
    }

    /**
     * 获取指定驱动器的缓存信息
     * @param   driveId
     *          驱动器 ID
     * @return  缓存信息
     */
    public CacheInfoDto findCacheInfo(Integer driveId) {
        int hitCount = zFileCache.getHitCount(driveId);
        int missCount = zFileCache.getMissCount(driveId);
        Set<String> keys = zFileCache.keySet(driveId);
        int cacheCount = keys.size();
        return new CacheInfoDto(cacheCount, hitCount, missCount, keys);
    }
}
