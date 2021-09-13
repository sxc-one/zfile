package top.ysxc.zfile.cache;

import cn.hutool.cache.impl.CacheObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ysxc.zfile.model.dto.FileItemDTO;
import top.ysxc.zfile.model.dto.SystemConfigDTO;
import top.ysxc.zfile.model.entity.DriveConfig;
import top.ysxc.zfile.repository.DriverConfigRepository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ysxc
 * @create 2021-09-01 4:28 下午
 */
@Component
@Slf4j
public class ZFileCache {

    @Resource
    private DriverConfigRepository driverConfigRepository;

    /**
     * 缓存过期时间
     */
    @Value("${zfile.cache.timeout}")
    private long timeout;

    /**
     * 缓存自动刷新间隔
     */
    @Value("${zfile.cache.auto-refresh.interval}")
    private long autoRefreshInterval;

    /**
     * 文件/文件对象缓存.
     *
     * ConcurrentMap<Integer, ConcurrentHashMap<String, List<FileItemDTO>>>
     * ConcurrentMap<driveId, ConcurrentHashMap<key, value>>
     *
     * driveId: 驱动器 ID
     * key: 文件夹路径
     * value: 文件夹中内容
     */
    private ConcurrentMap<Integer, MyTimedCache<DriveCacheKey, List<FileItemDTO>>> drivesCache = new ConcurrentHashMap<>();

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

    /**
     * 获取指定驱动器对应的缓存
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  驱动器对应的缓存
     */
    private synchronized MyTimedCache<DriveCacheKey, List<FileItemDTO>> getCacheByDriveId(Integer driveId) {
        MyTimedCache<DriveCacheKey, List<FileItemDTO>> driveCache = drivesCache.get(driveId);
        if (driveCache == null) {
            driveCache = new MyTimedCache<>(timeout * 1000);
            drivesCache.put(driveId, driveCache);
            startAutoCacheRefresh(driveId);
        }
        return driveCache;
    }

    /**
     * 获取指定驱动器的缓存命中数
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  缓存命中数
     */
    public int getHitCount(Integer driveId) {
        return getCacheByDriveId(driveId).getHitCount();
    }

    /**
     * 获取指定驱动器的缓存未命中数
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  缓存未命中数
     */
    public int getMissCount(Integer driveId) {
        return getCacheByDriveId(driveId).getMissCount();
    }

    /**
     * 获取所有缓存 key (文件夹名称)
     *
     * @return      所有缓存 key
     */
    public Set<String> keySet(Integer driveId) {
        Iterator<CacheObj<DriveCacheKey, List<FileItemDTO>>> cacheObjIterator = getCacheByDriveId(driveId).cacheObjIterator();
        Set<String> keys = new HashSet<>();
        while (cacheObjIterator.hasNext()) {
            keys.add(cacheObjIterator.next().getKey().getKey());
        }
        return keys;
    }

    /**
     * 从缓存中删除指定驱动器的某个路径的缓存
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @param   key
     *          文件夹路径
     */
    public void remove(Integer driveId, String key) {
        getCacheByDriveId(driveId).remove(new DriveCacheKey(driveId, key));
    }

    /**
     * 开启缓存自动刷新
     *
     * @param   driveId
     *          驱动器 ID
     */
    public void startAutoCacheRefresh(Integer driveId) {
        if (log.isDebugEnabled()) {
            log.debug("开启缓存自动刷新 driveId: {}", driveId);
        }
        DriveConfig driveConfig = driverConfigRepository.findById(driveId).get();
        Boolean autoRefreshCache = driveConfig.getAutoRefreshCache();
        if (autoRefreshCache != null && autoRefreshCache) {
            MyTimedCache<DriveCacheKey, List<FileItemDTO>> driveCache = drivesCache.get(driveId);
            if (driveCache == null) {
                driveCache = new MyTimedCache<>(timeout * 1000);
                drivesCache.put(driveId, driveCache);
            }
            driveCache.schedulePrune(autoRefreshInterval * 1000);
        }
    }

    public void stopAutoCacheRefresh(Integer driveId) {
        if (log.isDebugEnabled()) {
            log.debug("停止缓存自动刷新 driveId: {}", driveId);
        }
        MyTimedCache<DriveCacheKey, List<FileItemDTO>> driveCache = drivesCache.get(driveId);
        if (driveCache != null) {
            driveCache.cancelPruneSchedule();
        }
    }

    public void clear(Integer driveId) {
        if (log.isDebugEnabled()) {
            log.debug("清空驱动器所有缓存, driveId: {}", driveId);
        }
        getCacheByDriveId(driveId).clear();
    }

    /**
     * 获取指定驱动器, 某个文件夹的名称
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @param   key
     *          文件夹路径
     *
     * @return  驱动器中文件夹的内容
     */
    public List<FileItemDTO> get(Integer driveId, String key) {
        return getCacheByDriveId(driveId).get(new DriveCacheKey(driveId, key), false);
    }

    /**
     * 写入缓存
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @param   key
     *          文件夹路径
     *
     * @param   value
     *          文件夹中列表
     */
    public synchronized void put(Integer driveId, String key, List<FileItemDTO> value) {
        getCacheByDriveId(driveId).put(new DriveCacheKey(driveId, key), value);
    }
}
