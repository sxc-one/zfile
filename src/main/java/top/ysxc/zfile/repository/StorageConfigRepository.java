package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * 更新驱动器 ID 对应的参数设置为新的驱动器 ID
     *
     * @param   updateId
     *          驱动器原 ID
     *
     * @param   newId
     *          驱动器新 ID
     */
    @Modifying
    @Query(value="update STORAGE_CONFIG set driveId = :newId where driveId = :updateId")
    void updateDriveId(Integer updateId, Integer newId);
}
