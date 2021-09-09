package top.ysxc.zfile.controller.install;

import cn.hutool.crypto.SecureUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ysxc.zfile.model.dto.SystemConfigDTO;
import top.ysxc.zfile.model.support.ResultBean;
import top.ysxc.zfile.service.SystemConfigService;

import javax.annotation.Resource;

/**
 * 系统安装初始化
 * @author ysxc
 * @create 2021-09-09 3:04 下午
 */
@RestController
public class InstallController {

    @Resource
    private SystemConfigService systemConfigService;

    @GetMapping("/is-installed")
    public ResultBean isInstall() {
        if (!StringUtils.isEmpty(systemConfigService.getAdminUsername())) {
            return ResultBean.error("请勿重复初始化");
        }
        return ResultBean.success();
    }

    @PostMapping("/install")
    public ResultBean install(SystemConfigDTO systemConfigDTO) {
        if (!StringUtils.isEmpty(systemConfigService.getAdminUsername())) {
            return ResultBean.error("请勿重复初始化");
        }

        systemConfigDTO.setPassword(SecureUtil.md5(systemConfigDTO.getPassword()));
        systemConfigService.updateSystemConfig(systemConfigDTO);

        return ResultBean.success();
    }
}
