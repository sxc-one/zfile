package top.ysxc.zfile.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.service.base.AbstractBaseFileService;

import java.util.Map;

/**
 * 存储类型工厂类
 * @author ysxc
 * @create 2021-09-08 10:21 上午
 */
@Component
public class StorageTypeContext implements ApplicationContextAware {

    private static Map<String, AbstractBaseFileService> storageTypeEnumFileServiceMap;

    private static ApplicationContext applicationContext;


    /**
     * 项目启动时执行
     */
    @Override
    public void setApplicationContext(ApplicationContext act) throws BeansException {
        applicationContext = act;

        // 获取 Spring 容器中所有 FileService 类型的类
        storageTypeEnumFileServiceMap = act.getBeansOfType(AbstractBaseFileService.class);
    }

    /**
     * 获取指定存储类型 Service
     */
    public static AbstractBaseFileService getStorageTypeService(StorageTypeEnum type) {
        AbstractBaseFileService result = null;
        for (AbstractBaseFileService fileService : storageTypeEnumFileServiceMap.values()) {
            if (fileService.getStorageTypeEnum() == type) {
                result = fileService;
                break;
            }
        }
        return result;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
