package top.ysxc.zfile.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ysxc.zfile.model.entity.FilterConfig;
import top.ysxc.zfile.repository.FilterConfigRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-08 11:41 上午
 */
@Slf4j
@Service
public class FilterConfigService {

    @Resource
    private FilterConfigRepository filterConfigRepository;

    public List<FilterConfig> findByDriveId(Integer driveId) {
        return filterConfigRepository.findByDriveId(driveId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<FilterConfig> filterConfigList, Integer driveId) {
        filterConfigRepository.deleteByDriveId(driveId);
        filterConfigRepository.saveAll(filterConfigList);
    }
}
