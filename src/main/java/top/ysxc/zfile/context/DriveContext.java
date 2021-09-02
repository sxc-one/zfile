package top.ysxc.zfile.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import top.ysxc.zfile.exception.InvalidDriveException;
import top.ysxc.zfile.service.DriveConfigService;
import top.ysxc.zfile.service.base.AbstractBaseFileService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 每个驱动器对应一个 Service, 其中初始化好了与对象存储的连接信息.
 * 此驱动器上下文环境用户缓存每个 Service, 避免重复创建连接.
 * @author ysxc
 * @create 2021-09-02 10:56 上午
 */
@Component
@Slf4j
public class DriveContext implements ApplicationContextAware {

    /**
     * Map<Integer, AbstractBaseFileService>
     * Map<驱动器 ID, 驱动器连接 Service>
     */
    private static Map<Integer, AbstractBaseFileService> drivesServiceMap = new ConcurrentHashMap<>();

    @Resource
    private DriveConfigService driveConfigService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    /**
     * 获取指定驱动器的 Service.
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  驱动器对应的 Service
     */
    public AbstractBaseFileService get(Integer driveId) {
        AbstractBaseFileService abstractBaseFileService = drivesServiceMap.get(driveId);
        if (abstractBaseFileService == null) {
            throw new InvalidDriveException("此驱动器不存在或初始化失败, 请检查后台参数配置");
        }
        return abstractBaseFileService;
    }
}
