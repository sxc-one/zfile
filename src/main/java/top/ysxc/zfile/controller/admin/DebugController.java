package top.ysxc.zfile.controller.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ysxc.zfile.model.support.ResultBean;
import top.ysxc.zfile.service.SystemConfigService;

/**
 * @author ysxc
 * @create 2021-09-07 10:17 下午
 */
@Controller
public class DebugController {

    @Value("${zfile.debug}")
    private Boolean debug;

    private SystemConfigService systemConfigService;

    @ResponseBody
    @GetMapping("/debug/resetPwd")
    public ResultBean resetPwd() {
        if (debug) {
            systemConfigService.updateUsernameAndPwd("admin", "123456");
            return ResultBean.success();
        } else {
            return ResultBean.error("未开启 DEBUG 模式，不允许进行此操作。");
        }
    }
}
