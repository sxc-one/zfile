package top.ysxc.zfile.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ysxc.zfile.util.FileUtil;

import java.io.File;
import java.util.Date;

/**
 * @author ysxc
 * @create 2021-09-08 1:41 下午
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class LogController {

    /**
     * 系统日志下载
     */
    @GetMapping("/log")
    public ResponseEntity<Object> downloadLog() {
        if (log.isDebugEnabled()) {
            log.debug("下载诊断日志");
        }
        String userHome = System.getProperty("user.home");
        File fileZip = ZipUtil.zip(userHome + "/.zfile/logs");
        String currentDate = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        return FileUtil.exportSingleThread(fileZip, "ZFile 诊断日志 - " + currentDate + ".zip");
    }
}
