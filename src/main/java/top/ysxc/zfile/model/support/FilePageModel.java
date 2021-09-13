package top.ysxc.zfile.model.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.ysxc.zfile.model.dto.FileItemDTO;

import java.util.List;

/**
 * @author zhaojun
 */
@Data
@AllArgsConstructor
public class FilePageModel {

    private int totalPage;

    private List<FileItemDTO> fileList;

}