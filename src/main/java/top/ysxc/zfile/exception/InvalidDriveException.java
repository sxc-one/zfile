package top.ysxc.zfile.exception;

/**
 * @author ysxc
 * @create 2021-09-02 11:42 上午
 */
public class InvalidDriveException extends RuntimeException {
    public InvalidDriveException() {
    }

    public InvalidDriveException(String message) {
        super(message);
    }

    public InvalidDriveException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDriveException(Throwable cause) {
        super(cause);
    }

    public InvalidDriveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
