package top.ysxc.zfile.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.ysxc.zfile.model.dto.SystemConfigDTO;
import top.ysxc.zfile.service.SystemConfigService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

/**
 * @author ysxc
 * @create 2021-09-13 1:47 下午
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SystemConfigService systemConfigService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemConfigDTO systemConfig = systemConfigService.getSystemConfig();
        if (!Objects.equals(systemConfig.getUsername(), username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new User(systemConfig.getUsername(), systemConfig.getPassword(), Collections.emptyList());
    }
}
