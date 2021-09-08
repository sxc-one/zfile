package top.ysxc.zfile.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ysxc.zfile.context.StorageTypeContext;
import top.ysxc.zfile.model.entity.StorageConfig;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.model.support.ResultBean;
import top.ysxc.zfile.service.base.AbstractBaseFileService;

import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-08 1:54 下午
 */
@RestController
@RequestMapping("/admin")
public class MateDataController {

    /**
     * 返回支持的存储引擎.
     */
    @GetMapping("/support-strategy")
    public ResultBean supportStrategy() {
        StorageTypeEnum[] values = StorageTypeEnum.values();
        return ResultBean.success(values);
    }

    /**
     * 获取指定存储策略的表单域
     *
     * @param   storageType
     *          存储策略
     *
     * @return  所有表单域
     */
    @GetMapping("/strategy-form")
    public ResultBean getFormByStorageType(StorageTypeEnum storageType) {
        AbstractBaseFileService storageTypeService = StorageTypeContext.getStorageTypeService(storageType);
        List<StorageConfig> storageConfigList = storageTypeService.storageStrategyConfigList();
        return ResultBean.success(storageConfigList);
    }
}
