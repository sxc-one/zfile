package top.ysxc.zfile.controller.admin;

import org.springframework.web.bind.annotation.*;
import top.ysxc.zfile.model.dto.DriveConfigDTO;
import top.ysxc.zfile.model.entity.DriveConfig;
import top.ysxc.zfile.model.support.ResultBean;
import top.ysxc.zfile.service.DriveConfigService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 驱动器相关操作 Controller
 * @author ysxc
 * @create 2021-09-07 10:21 下午
 */
@RestController
@RequestMapping("/admin")
public class DriveController {

    @Resource
    private DriveConfigService driveConfigService;

//    @Resource
//    private FilterConfigService filterConfigService;

    /**
     * 获取所有驱动器列表
     *
     * @return  驱动器列表
     */
    @GetMapping("/drives")
    public ResultBean driveList() {
        List<DriveConfig> list = driveConfigService.list();
        return ResultBean.success(list);
    }

    /**
     * 获取指定驱动器基本信息及其参数
     *
     * @param   driveId
     *          驱动器 ID
     *
     * @return  驱动器基本信息
     */
    @GetMapping("/drive/{driveId}")
    public ResultBean driveItem(@PathVariable Integer driveId) {
        DriveConfigDTO driveConfig = driveConfigService.findDriveConfigDTOById(driveId);
        return ResultBean.success(driveConfig);
    }

    /**
     * 保存驱动器设置
     */
    @PostMapping("/drive")
    public ResultBean saveDriveItem(@RequestBody DriveConfigDTO driveConfigDTO) {
        driveConfigService.saveDriveConfigDTO(driveConfigDTO);
        return ResultBean.success();
    }
}
