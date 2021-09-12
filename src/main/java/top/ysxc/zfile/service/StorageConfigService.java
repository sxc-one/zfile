package top.ysxc.zfile.service;

import org.springframework.stereotype.Service;
import top.ysxc.zfile.model.entity.StorageConfig;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.repository.StorageConfigRepository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ysxc
 * @create 2021-09-12 8:18 下午
 */
@Service
public class StorageConfigService {

    @Resource
    private StorageConfigRepository storageConfigRepository;

    public List<StorageConfig> selectStorageConfigByType(StorageTypeEnum storageTypeEnum) {
        return storageConfigRepository.findByTypeOrderById(storageTypeEnum);
    }

    public List<StorageConfig> selectStorageConfigByDriveId(Integer driveId) {
        return storageConfigRepository.findByDriveIdOrderById(driveId);
    }

    public StorageConfig findByDriveIdAndKey(Integer driveId, String key) {
        return storageConfigRepository.findByDriveIdAndKey(driveId, key);
    }

    public Map<String, StorageConfig> selectStorageConfigMapByKey(StorageTypeEnum storageTypeEnum) {
        Map<String, StorageConfig> map = new HashMap<>(24);
        for (StorageConfig storageConfig : selectStorageConfigByType(storageTypeEnum)) {
            map.put(storageConfig.getKey(), storageConfig);
        }
        return map;
    }

    public Map<String, StorageConfig> selectStorageConfigMapByDriveId(Integer driveId) {
        Map<String, StorageConfig> map = new HashMap<>(24);
        for (StorageConfig storageConfig : selectStorageConfigByDriveId(driveId)) {
            map.put(storageConfig.getKey(), storageConfig);
        }
        return map;
    }

    public void updateStorageConfig(List<StorageConfig> storageConfigList) {
        storageConfigRepository.saveAll(storageConfigList);
    }
}
