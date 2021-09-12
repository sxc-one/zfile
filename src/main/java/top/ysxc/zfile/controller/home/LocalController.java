package top.ysxc.zfile.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import top.ysxc.zfile.context.DriveContext;
import top.ysxc.zfile.model.constant.ZFileConstant;
import top.ysxc.zfile.service.impl.LocalServiceImpl;
import top.ysxc.zfile.util.FileUtil;
import top.ysxc.zfile.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 本地存储 Controller
 * @author ysxc
 * @create 2021-09-12 8:01 下午
 */
@Controller
public class LocalController {

    @Resource
    private DriveContext driveContext;

    /**
     * 本地存储下载指定文件
     *
     * @param   driveId
     *          驱动器 ID
     * @param   type
     *          附件预览类型:
     *              download:下载
     *              default: 浏览器默认行为
     */
    @GetMapping("/file/{driveId}/**")
    @ResponseBody
    public void downAttachment(@PathVariable("driveId") Integer driveId, String type, final HttpServletRequest request, final HttpServletResponse response) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher apm = new AntPathMatcher();
        String filePath = apm.extractPathWithinPattern(bestMatchPattern, path);
        LocalServiceImpl localService = (LocalServiceImpl) driveContext.get(driveId);
        File file = new File(StringUtils.removeDuplicateSeparator(localService.getFilePath() + ZFileConstant.PATH_SEPARATOR + filePath));
        FileUtil.export(request, response, file, type);
    }
}
