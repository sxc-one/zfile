package top.ysxc.zfile.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import top.ysxc.zfile.exception.InitializeDriveException;
import top.ysxc.zfile.exception.NotExistFileException;
import top.ysxc.zfile.model.constant.StorageConfigConstant;
import top.ysxc.zfile.model.constant.SystemConfigConstant;
import top.ysxc.zfile.model.constant.ZFileConstant;
import top.ysxc.zfile.model.dto.FileItemDTO;
import top.ysxc.zfile.model.entity.StorageConfig;
import top.ysxc.zfile.model.entity.SystemConfig;
import top.ysxc.zfile.model.enums.FileTypeEnum;
import top.ysxc.zfile.model.enums.StorageTypeEnum;
import top.ysxc.zfile.repository.SystemConfigRepository;
import top.ysxc.zfile.service.StorageConfigService;
import top.ysxc.zfile.service.base.AbstractBaseFileService;
import top.ysxc.zfile.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author ysxc
 * @create 2021-09-12 8:07 下午
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LocalServiceImpl extends AbstractBaseFileService {

    private static final Logger log = LoggerFactory.getLogger(LocalServiceImpl.class);

    @Resource
    private StorageConfigService storageConfigService;

    @Resource
    private SystemConfigRepository systemConfigRepository;

    private String filePath;

    @Override
    public StorageTypeEnum getStorageTypeEnum() {
        return StorageTypeEnum.LOCAL;
    }

    @Override
    public List<StorageConfig> storageStrategyConfigList() {
        return new ArrayList<StorageConfig>() {{
            add(new StorageConfig("filePath", "文件路径"));
        }};
    }

    @Override
    public void init(Integer driveId) {
        this.driveId = driveId;
        Map<String, StorageConfig> stringStorageConfigMap = storageConfigService.selectStorageConfigMapByDriveId(driveId);
        filePath = stringStorageConfigMap.get(StorageConfigConstant.FILE_PATH_KEY).getValue();
        if (Objects.isNull(filePath)) {
            log.debug("初始化存储策略 [{}] 失败: 参数不完整", getStorageTypeEnum().getDescription());
            isInitialized = false;
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new InitializeDriveException("文件路径: \"" + file.getAbsolutePath() + "\"不存在, 请检查是否填写正确.");
        } else {
            testConnection();
            isInitialized = true;
        }
    }

    @Override
    public FileItemDTO getFileItem(String path) {
        String fullPath = filePath + path;

        File file = new File(fullPath);

        if (!file.exists()) {
            throw new NotExistFileException();
        }

        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setType(file.isDirectory() ? FileTypeEnum.FOLDER : FileTypeEnum.FILE);
        fileItemDTO.setTime(new Date(file.lastModified()));
        fileItemDTO.setSize(file.length());
        fileItemDTO.setName(file.getName());
        fileItemDTO.setPath(filePath);
        if (file.isFile()) {
            fileItemDTO.setUrl(getDownloadUrl(path));
        }

        return fileItemDTO;
    }

    @Override
    public List<FileItemDTO> fileList(String path) throws FileNotFoundException {
        List<FileItemDTO> fileItemList = new ArrayList<>();

        String fullPath = StringUtils.removeDuplicateSeparator(filePath + path);

        File file = new File(fullPath);

        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }

        File[] files = file.listFiles();

        if (files == null) {
            return fileItemList;
        }

        for (File f : files) {
            FileItemDTO fileItemDTO = new FileItemDTO();
            fileItemDTO.setType(f.isDirectory() ? FileTypeEnum.FOLDER : FileTypeEnum.FILE);
            fileItemDTO.setTime(new Date(f.lastModified()));
            fileItemDTO.setSize(f.length());
            fileItemDTO.setName(f.getName());
            fileItemDTO.setPath(path);
            if (f.isFile()) {
                fileItemDTO.setUrl(getDownloadUrl(StringUtils.concatUrl(path, f.getName())));
            }
            fileItemList.add(fileItemDTO);
        }
        return fileItemList;
    }

    @Override
    public String getDownloadUrl(String path) {
        SystemConfig usernameConfig = systemConfigRepository.findByKey(SystemConfigConstant.DOMAIN);
        return StringUtils.removeDuplicateSeparator(usernameConfig.getValue() + "/file/" + driveId + ZFileConstant.PATH_SEPARATOR + path);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
