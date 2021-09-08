package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ysxc.zfile.model.entity.StorageConfig;

import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-07 10:48 下午
 */
@Repository
public interface StorageConfigRepository extends JpaRepository<StorageConfig, Integer> {
    /**
     * 根据驱动器找到对应的配置信息
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  此驱动器所有的配置信息
     */
    List<StorageConfig> findByDriveId(Integer driveId);

    /**
     * 删除指定驱动器对应的配置信息
     *
     * @param   driveId
     *          驱动器 ID
     */
    void deleteByDriveId(Integer driveId);
}
