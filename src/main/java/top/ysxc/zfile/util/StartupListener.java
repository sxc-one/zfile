package top.ysxc.zfile.util;

import cn.hutool.core.net.NetUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;

/**
 * 项目启动监听器, 当项目启动时, 遍历当前对象存储的所有内容, 添加到缓存中.
 * @author ysxc
 * @create 2021-09-13 2:38 下午
 */
@Component
@Slf4j
public class StartupListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private Environment environment;

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        printStartInfo();
    }

    private void printStartInfo() {
        String serverPort = environment.getProperty("server.port", "8080");

        LinkedHashSet<String> localIps = NetUtil.localIps();
        StringBuilder indexAddr = new StringBuilder();
        StringBuilder indexAdminAddr = new StringBuilder();
        for (String localIp : localIps) {
            String addr = String.format("http://%s:%s", localIp, serverPort);
            indexAddr.append(addr).append("\t");
            indexAdminAddr.append(addr).append("/#/admin").append("\t");
        }
        log.info("ZFile started at          " + indexAddr);
        log.info("ZFile Admin started at    " + indexAdminAddr);
    }
}
