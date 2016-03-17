package com.roirrow.common.exception;

import com.roirrow.common.exception.constant.ExceptionLevel;

import java.util.Map;

/**
 * 
 * <Description>统一的异常定义,默认异常级别是轻微 @see ExceptionLevel<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class UnifiedException extends Exception{

    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = 19930304L;

    /**
     * 异常级别
     */
    private ExceptionLevel level = ExceptionLevel.SLIGHT;

    /**
     * 异常错误信息
     */
    private String errorMessage = "";

    /**
     * 业务模块
     */
    private String businessModule = "未知";

    /**
     * 异常上下文，可以设置一些关键业务参数
     */
    private Map<String, Object> context;

    /**
     * 自定义errorCode,可根据这个errorCode做异常的筛选、特殊处理
     */
    private String errorCode = "0";

    /**
     * 是否已处理过
     */
    private boolean isHandled = false;

    /**
     * 构造统一异常
     *
     * @param level          异常级别
     * @see com.alibaba.nazca.commonservice.exception.constant.ExceptionLevel
     * @param errorMessage   异常errorMessage
     * @param errorCode      异常errorCode
     * @param businessModule 异常业务模块
     * @param context        异常上下文，可以设置关键业务参数
     * @param cause          the throwable which caused this UnifiedException
     */
    public UnifiedException(ExceptionLevel level, String errorMessage, String errorCode, String businessModule,
                            Map<String, Object> context, Throwable cause) {
        super(cause);
        this.level = level;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.businessModule = businessModule;
        this.context = context;
    }


    /**
     * get level
     * @return Returns the level.<br>
     */
    public ExceptionLevel getLevel() {
        return level;
    }

    /**
     * set level
     * @param level The level to set. <br>
     */
    public void setLevel(ExceptionLevel level) {
        this.level = level;
    }

    /**
     * get errorMessage
     * @return Returns the errorMessage.<br>
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * set errorMessage
     * @param errorMessage The errorMessage to set. <br>
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * get businessModule
     * @return Returns the businessModule.<br>
     */
    public String getBusinessModule() {
        return businessModule;
    }

    /**
     * set businessModule
     * @param businessModule The businessModule to set. <br>
     */
    public void setBusinessModule(String businessModule) {
        this.businessModule = businessModule;
    }

    /**
     * get errorCode
     * @return Returns the errorCode.<br>
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * set errorCode
     * @param errorCode The errorCode to set. <br>
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * get isHandled
     * @return Returns the isHandled.<br>
     */
    public boolean isHandled() {
        return isHandled;
    }

    /**
     * set isHandled
     * @param isHandled The isHandled to set. <br>
     */
    public void setHandled(boolean isHandled) {
        this.isHandled = isHandled;
    }

    /**
     * Getter method for property <tt>context</tt>.
     *
     * @return property value of context
     */
    public Map<String, Object> getContext() {
        return context;
    }

    /**
     * Setter method for property <tt>context</tt>.
     *
     * @param context value to be assigned to property context
     */
    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}
