package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ysxc.zfile.model.entity.DriveConfig;

/**
 * @author ysxc
 * @create 2021-09-01 7:36 下午
 */
@Repository
public interface DriverConfigRepository extends JpaRepository<DriveConfig, Integer> {

    /**
     * 查询驱动器最大的 ID
     *
     * @return  驱动器最大 ID
     */
    @Query(nativeQuery = true, value = "select max(id) max from DRIVER_CONFIG")
    Integer selectMaxId();

    /**
     * 更新驱动器 ID
     *
     * @param   updateId
     *          驱动器原 ID
     *
     * @param   newId
     *          驱动器新 ID
     */
    @Modifying
    @Query(value = "update DRIVER_CONFIG set id = :newId where id = :updateId")
    void updateId(Integer updateId, Integer newId);
}
