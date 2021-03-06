package top.ysxc.zfile.controller.admin;

import org.springframework.web.bind.annotation.*;
import top.ysxc.zfile.model.dto.CacheInfoDto;
import top.ysxc.zfile.model.support.ResultBean;
import top.ysxc.zfile.service.DriveConfigService;

import javax.annotation.Resource;

/**
 * @author ysxc
 * @create 2021-09-01 7:26 下午
 */
@RestController
@RequestMapping("/admin/cache")
public class CacheController {

    @Resource
    private DriveConfigService driveConfigService;

    @PostMapping("/{driveId}/enable")
    public ResultBean enableCache(@PathVariable("driveId") Integer driveId) {
        driveConfigService.updateCacheStatus(driveId, true);
        return ResultBean.success();
    }

    @PostMapping("/{driveId}/disable")
    public ResultBean disableCache(@PathVariable("driveId") Integer driveId) {
        driveConfigService.updateCacheStatus(driveId, false);
        return ResultBean.success();
    }

    @GetMapping("/{driveId}/info")
    public ResultBean cacheInfo(@PathVariable("driveId") Integer driveId) {
        CacheInfoDto cacheInfo = driveConfigService.findCacheInfo(driveId);
        return ResultBean.success(cacheInfo);
    }

    @PostMapping("/{driveId}/refresh")
    public ResultBean refreshCache(@PathVariable("driveId") Integer driveId, String key) throws Exception{
        driveConfigService.refreshCache(driveId, key);
        return ResultBean.success();
    }

    @PostMapping("/{driveId}/auto-refresh/start")
    public ResultBean enableAutoRefresh(@PathVariable("driveId") Integer driveId) {
        driveConfigService.startAutoCacheRefresh(driveId);
        return ResultBean.success();
    }

    @PostMapping("/{driveId}/auto-refresh/stop")
    public ResultBean disableAutoRefresh(@PathVariable("driveId") Integer driveId) {
        driveConfigService.stopAutoCacheRefresh(driveId);
        return ResultBean.success();
    }

    @PostMapping("/{driveId}/clear")
    public ResultBean clearCache(@PathVariable("driveId") Integer driveId) {
        driveConfigService.clearCache(driveId);
        return ResultBean.success();
    }
}
