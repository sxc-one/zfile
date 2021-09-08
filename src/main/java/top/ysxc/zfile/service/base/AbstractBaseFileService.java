package top.ysxc.zfile.service.base;

import lombok.extern.slf4j.Slf4j;
import top.ysxc.zfile.cache.ZFileCache;
import top.ysxc.zfile.model.entity.StorageConfig;
import top.ysxc.zfile.model.enums.StorageTypeEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-02 11:04 上午
 */
@Slf4j
public abstract class AbstractBaseFileService implements BaseFileService {

    @Resource
    private ZFileCache zFileCache;

    /**
     * 是否初始化成功
     */
    protected boolean isInitialized = false;

    /**
     * 获取是否初始化成功
     *
     * @return  初始化成功与否
     */
    public boolean getIsUnInitialized() {
        return !isInitialized;
    }

    /**
     * 获取当前实现类的存储策略类型
     *
     * @return  存储策略类型枚举对象
     */
    public abstract StorageTypeEnum getStorageTypeEnum();

    /**
     * 获取初始化当前存储策略, 所需要的参数信息 (用于表单填写)
     *
     * @return  初始化所需的参数列表
     */
    public abstract List<StorageConfig> storageStrategyConfigList();

    /**
     * 初始化方法, 启动时自动调用实现类的此方法进行初始化.
     *
     * @param   driveId
     *          驱动器 ID
     */
    public abstract void init(Integer driveId);
}
