package top.ysxc.zfile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.ysxc.zfile.model.entity.DriveConfig;

import java.util.List;

/**
 * @author ysxc
 * @create 2021-09-10 10:32 上午
 */
@Data
@AllArgsConstructor
public class DriveListDTO {

    private List<DriveConfig> driveList;

    private Boolean isInstall;
}
