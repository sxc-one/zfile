package top.ysxc.zfile.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.ysxc.zfile.service.ShortLinkConfigService;

import javax.annotation.Resource;

/**
 * @author ysxc
 * @create 2021-09-08 2:14 下午
 */
@Controller
@RequestMapping("/admin")
public class ShortLinkManagerController {

    @Resource
    private ShortLinkConfigService shortLinkConfigService;


}
