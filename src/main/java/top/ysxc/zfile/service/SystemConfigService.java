package top.ysxc.zfile.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ysxc.zfile.cache.ZFileCache;
import top.ysxc.zfile.model.constant.SystemConfigConstant;
import top.ysxc.zfile.model.dto.SystemConfigDTO;
import top.ysxc.zfile.model.entity.SystemConfig;
import top.ysxc.zfile.repository.SystemConfigRepository;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-01 4:23 下午
 */
@Slf4j
@Service
public class SystemConfigService {

    @Resource
    private ZFileCache zFileCache;

    @Resource
    private SystemConfigRepository systemConfigRepository;

    private Class<SystemConfigDTO> systemConfigClazz = SystemConfigDTO.class;

    public SystemConfigDTO getSystemConfig() {
        SystemConfigDTO cacheConfig = zFileCache.getConfig();
        if (cacheConfig != null) {
            return cacheConfig;
        }

        SystemConfigDTO systemConfigDTO = new SystemConfigDTO();
        List<SystemConfig> systemConfigList = systemConfigRepository.findAll();

        for (SystemConfig systemConfig : systemConfigList) {
            String key = systemConfig.getKey();

            try {
                Field field = systemConfigClazz.getDeclaredField(key);
                field.setAccessible(true);
                String strVal = systemConfig.getValue();
                Object convertVal = Convert.convert(field.getType(), strVal);
                field.set(systemConfigDTO, convertVal);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("通过反射, 将字段 {} 注入 SystemConfigDTO 时出现异常:", key, e);
            }
        }

        zFileCache.updateConfig(systemConfigDTO);
        return systemConfigDTO;
    }

    public void updateSystemConfig(SystemConfigDTO systemConfigDTO) {
        List<SystemConfig> systemConfigList = new ArrayList<>();

        Field[] fields = systemConfigClazz.getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();
            SystemConfig systemConfig = systemConfigRepository.findByKey(key);
            if (systemConfig != null) {
                field.setAccessible(true);
                Object val = null;
                try {
                    val = field.get(systemConfigDTO);
                } catch (IllegalAccessException e) {
                    log.error("通过反射, 从 SystemConfigDTO 获取字段 {}  时出现异常:", key, e);
                }
                if (val != null) {
                    systemConfig.setValue(val.toString());
                    systemConfigList.add(systemConfig);
                }
            }
        }

        zFileCache.removeConfig();
        systemConfigRepository.saveAll(systemConfigList);
    }

    public void updateUsernameAndPwd(String username, String password) {
        SystemConfig usernameConfig = systemConfigRepository.findByKey(SystemConfigConstant.USERNAME);
        usernameConfig.setValue(username);
        systemConfigRepository.save(usernameConfig);

        String encryptionPassword = SecureUtil.md5(password);
        SystemConfig systemConfig = systemConfigRepository.findByKey(SystemConfigConstant.PASSWORD);
        systemConfig.setValue(encryptionPassword);

        zFileCache.removeConfig();

        systemConfigRepository.save(systemConfig);
    }

    /**
     * 获取管理员名称
     *
     * @return  管理员名称
     */
    public String getAdminUsername() {
        SystemConfigDTO systemConfigDTO = getSystemConfig();
        return systemConfigDTO.getUsername();
    }
}
