package top.ysxc.zfile.cache;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.TimedCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author ysxc
 * @create 2021-09-02 10:05 上午
 */
@Slf4j
public class MyTimedCache<K, V> extends TimedCache<K, V> {
    public MyTimedCache(long timeout) {
        super(timeout);
    }

    public MyTimedCache(long timeout, Map<K, CacheObj<K, V>> map) {
        super(timeout, map);
    }
}
