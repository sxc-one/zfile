package top.ysxc.zfile.exception;

/**
 * @author ysxc
 * @create 2021-09-09 3:37 下午
 */
public class TextParseException extends RuntimeException {

    public TextParseException() {
        super();
    }

    public TextParseException(String message) {
        super(message);
    }

    public TextParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextParseException(Throwable cause) {
        super(cause);
    }

    protected TextParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
