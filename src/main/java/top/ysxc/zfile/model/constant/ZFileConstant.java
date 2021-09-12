package top.ysxc.zfile.model.constant;

import org.springframework.context.annotation.Configuration;

/**
 * @author ysxc
 * @create 2021-09-08 1:18 下午
 */
@Configuration
public class ZFileConstant {

    public static final String USER_HOME = System.getProperty("user.home");

    public static final Character PATH_SEPARATOR_CHAR = '/';

    public static final String PATH_SEPARATOR = "/";

    /**
     * 直链前缀名称
     */
    public static final String DIRECT_LINK_PREFIX = "directlink";

    /**
     * 系统产生的临时文件路径
     */
    public static String TMP_FILE_PATH = "/.zfile/tmp2/";

    /**
     * 页面文档文件
     */
    public static String README_FILE_NAME = "readme.md";

    /**
     * 密码文件
     */
    public static String PASSWORD_FILE_NAME = "password.txt";

    /**
     * 最大支持文件大小为 ? MB 的音乐文件解析封面, 歌手等信息.
     */
    public static Long AUDIO_MAX_FILE_SIZE_MB = 1L;

    /**
     * 最大支持文本文件大小为 ? KB 的文件内容.
     */
    public static Long TEXT_MAX_FILE_SIZE_KB = 100L;
}
