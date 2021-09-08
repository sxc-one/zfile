package top.ysxc.zfile.util;

import cn.hutool.core.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;

/**
 * @author ysxc
 * @create 2021-09-08 1:46 下午
 */
@Slf4j
public class FileUtil {

    public static ResponseEntity<Object> exportSingleThread(File file, String fileName) {
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 FILE NOT FOUND");
        }

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");

        if (StringUtils.isNullOrEmpty(fileName)) {
            fileName = file.getName();
        }

        headers.setContentDispositionFormData("attachment", URLUtil.encode(fileName));

        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
        headers.add(HttpHeaders.LAST_MODIFIED, new Date().toString());
        headers.add(HttpHeaders.ETAG, String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(mediaType)
                .body(new FileSystemResource(file));
    }
}
