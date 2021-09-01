package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ysxc.zfile.model.entity.SystemConfig;

/**
 * @author ysxc
 * @create 2021-09-01 4:40 下午
 */
@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Integer> {

    /**
     * 查找系统设置中, 某个设置项对应的值
     *
     * @param   key
     *          设置项
     *
     * @return  设置值
     */
    SystemConfig findByKey(String key);
}
