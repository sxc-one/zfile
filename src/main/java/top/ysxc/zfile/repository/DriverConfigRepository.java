package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ysxc.zfile.model.entity.DriveConfig;

/**
 * @author ysxc
 * @create 2021-09-01 7:36 下午
 */
@Repository
public interface DriverConfigRepository extends JpaRepository<DriveConfig, Integer> {

}