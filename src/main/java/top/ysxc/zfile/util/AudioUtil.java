package top.ysxc.zfile.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.mpatric.mp3agic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ysxc.zfile.model.constant.ZFileConstant;
import top.ysxc.zfile.model.dto.AudioInfoDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * 音频解析工具类
 * @author zhaojun
 */
public class AudioUtil {

    private static final Logger log = LoggerFactory.getLogger(AudioUtil.class);

    public static AudioInfoDTO getAudioInfo(String url) throws Exception {
        String query = new URL(URLUtil.decode(url)).getQuery();

        if (query != null) {
            url = url.replace(query, URLUtil.encode(query));
        }

        // 如果音乐文件大小超出 5M, 则不解析此音乐
        if (top.ysxc.zfile.util.HttpUtil.getRemoteFileSize(url)
                > (1024 * 1024 * ZFileConstant.AUDIO_MAX_FILE_SIZE_MB)) {
            return AudioInfoDTO.buildDefaultAudioInfoDTO();
        }

        String fullFilePath = StringUtils.removeDuplicateSeparator(ZFileConstant.TMP_FILE_PATH + ZFileConstant.PATH_SEPARATOR + UUID.fastUUID());

        File file = new File(fullFilePath);
        FileUtil.mkParentDirs(file);
        HttpUtil.downloadFile(url, file);
        AudioInfoDTO audioInfoDTO = parseAudioInfo(file);
        audioInfoDTO.setSrc(url);
        file.deleteOnExit();
        return audioInfoDTO;
    }

    private static AudioInfoDTO parseAudioInfo(File file) throws IOException, UnsupportedTagException {
        AudioInfoDTO audioInfoDTO = AudioInfoDTO.buildDefaultAudioInfoDTO();

        Mp3File mp3File = null;
        try {
            mp3File = new Mp3File(file);
        } catch (InvalidDataException e) {
            if (log.isDebugEnabled()) {
                log.debug("无法解析的音频文件.");
            }
        }

        if (mp3File == null) {
            return audioInfoDTO;
        }

        ID3v1 audioTag = null;

        if (mp3File.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            byte[] albumImage = id3v2Tag.getAlbumImage();
            if (albumImage != null) {
                audioInfoDTO.setCover("data:" + id3v2Tag.getAlbumImageMimeType() + ";base64," + Base64.encode(albumImage));
            }
            audioTag = id3v2Tag;
        }

        if (audioTag != null) {
            audioInfoDTO.setTitle(audioTag.getTitle());
            audioInfoDTO.setArtist(audioTag.getArtist());
        }

        return audioInfoDTO;
    }
}
