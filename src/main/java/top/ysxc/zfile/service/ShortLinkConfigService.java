package top.ysxc.zfile.service;

import org.springframework.stereotype.Service;
import top.ysxc.zfile.model.entity.ShortLinkConfig;
import top.ysxc.zfile.repository.ShortLinkConfigRepository;

import javax.annotation.Resource;

/**
 * @author ysxc
 * @create 2021-09-08 2:15 下午
 */
@Service
public class ShortLinkConfigService {

    @Resource
    private ShortLinkConfigRepository shortLinkConfigRepository;

    public ShortLinkConfig findByKey(String key) {
        return shortLinkConfigRepository.findFirstByKey(key);
    }
}
