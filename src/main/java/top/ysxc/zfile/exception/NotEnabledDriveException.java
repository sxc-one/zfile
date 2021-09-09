package top.ysxc.zfile.exception;

/**
 * 未启用的驱动器异常
 * @author ysxc
 * @create 2021-09-09 3:19 下午
 */
public class NotEnabledDriveException extends RuntimeException {
    public NotEnabledDriveException() {
    }

    public NotEnabledDriveException(String message) {
        super(message);
    }

    public NotEnabledDriveException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnabledDriveException(Throwable cause) {
        super(cause);
    }

    public NotEnabledDriveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
