package com.roirrow.common.exception.resolver;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * <Description>公用返回值,可以设置为自己公司的统一返回值<br>
 *  @see com.roirrow.common.exception.resolver.ResponseVo
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月20日 <br>
 */
public class ResponseVo {
    // 返回数据集
    private Object data;

    // 成功标记
    private boolean success = true;

    // 提示信息
    private String msg = "";

    // 错误码
    private int errorCode ;
    
    /**
     * 缺省构造函数
     */
    public ResponseVo() {
        
    }
    /**
     * 
     * @param success
     * @param msg
     * @param errorCode
     */
    public ResponseVo(boolean success,String msg,int errorCode) {
            this.success = success;
            this.msg = msg;
            this.errorCode = errorCode;
    }
    /**
     * 设置错误信息
     * @param msg
     * @param errorCode
     */
    public void setError(String msg,int errorCode) {
        this.success = false;
        this.msg = msg;
        this.errorCode = errorCode;
    }
    
    /**
     * 
     * @return :
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *            :
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return :
     */
    public Object getData() {
        if (null == data) {
            return new HashMap<String, Object>();
        }
        return data;
    }

    /**
     * 
     * @param rows
     *            :
     * @param count
     *            :
     */
    public void setData(Object rows, int count, boolean flag) {
        if (flag) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rows", rows);
            map.put("count", count);
            this.data = map;
        } else {
            this.data = rows;
        }

    }

    /**
     * 
     * @param success
     *            :
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * msg
     * 
     * @return the msg
     */

    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /** 
     * set data
     * @param data The data to set. <br>
     */
    public void setData(Object data) {
        this.data = data;
    }
    /** 
     * get success
     * @return Returns the success.<br> 
     */
    public boolean isSuccess() {
        return success;
    }
}
