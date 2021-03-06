package top.ysxc.zfile.model.support;

import java.io.Serializable;

/**
 * @author ysxc
 * @create 2021-09-01 4:03 下午
 */
public class ResultBean implements Serializable {

    private static final long serialVersionUID = 4655188732867481650L;

    public static final int SUCCESS = 0;

    public static final int FAIL = -1;

    public static final int REQUIRED_PASSWORD = -2;

    public static final int INVALID_PASSWORD = -3;

    private String msg = "操作成功";

    private int code = SUCCESS;

    private Object data;

    public ResultBean() {
        super();
    }

    public ResultBean(String msg, Object data, int code) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static ResultBean success() {
        return success("操作成功");
    }

    public static ResultBean success(String msg) {
        return success(msg, null);
    }

    public static ResultBean success(Object data) {
        return success("操作成功", data);
    }

    public static ResultBean success(String msg, Object data) {
        return new ResultBean(msg, data, SUCCESS);
    }

    public static ResultBean successData(Object data) {
        return success("操作成功", data);
    }

    public static ResultBean successPage(Object data, Long total) {
        return success("操作成功", data);
    }

    public static ResultBean error(String msg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(FAIL);
        resultBean.setMsg(msg);
        return resultBean;
    }

    public static ResultBean error(String msg, Integer code) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMsg(msg);
        return resultBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
