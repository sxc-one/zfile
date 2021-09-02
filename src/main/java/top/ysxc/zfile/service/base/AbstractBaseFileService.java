package top.ysxc.zfile.service.base;

import lombok.extern.slf4j.Slf4j;
import top.ysxc.zfile.cache.ZFileCache;

import javax.annotation.Resource;

/**
 * @author ysxc
 * @create 2021-09-02 11:04 上午
 */
@Slf4j
public abstract class AbstractBaseFileService implements BaseFileService {

    @Resource
    private ZFileCache zFileCache;



}
