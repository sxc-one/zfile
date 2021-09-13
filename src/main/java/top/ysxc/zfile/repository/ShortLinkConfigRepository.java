package top.ysxc.zfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ysxc.zfile.model.entity.ShortLinkConfig;

/**
 * @author ysxc
 * @create 2021-09-08 1:22 下午
 */
@Repository
public interface ShortLinkConfigRepository extends JpaRepository<ShortLinkConfig, Integer>, JpaSpecificationExecutor<ShortLinkConfig> {

    /**
     * 获取驱动器下的所有规则
     *
     * @param       key
     *              短链 Key
     */
    ShortLinkConfig findFirstByKey(String key);

    /**
     * 获取驱动器下的所有规则
     *
     * @param       url
     *              短链 URL
     */
    ShortLinkConfig findFirstByUrl(String url);

    /**
     * 更新驱动器 ID
     *
     * @param   updateSubPath
     *          原路径部分名称
     *
     * @param   newSubPath
     *          修改后路径部分名称
     */
    @Modifying
    @Query(value = "update SHORT_LINK set url = replace(url, :updateSubPath, :newSubPath)")
    void updateUrlDriveId(String updateSubPath, String newSubPath);
}
