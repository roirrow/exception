package com.roirrow.common.exception.vo;

import org.springframework.core.task.TaskExecutor;

/**
 * <Description>异常处理全局配置<br>
 * 
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class GlobalInfo {
    /**
     * 系统三字码
     */
    private String systemCode;

    /**
     * 是否最后一个异常处理器
     */
    private boolean isLast;
    
    /**
     * 异步处理线程池
     */
    private TaskExecutor exceptionHandleExecutor;

    /**
     * 构造器
     * @param systemCode code of the current system
     * @param isLast identifying if this exception handler is the last handler
     * @param exceptionHandleExecutor asyn thread pool
     */
    public GlobalInfo(String systemCode, boolean isLast,TaskExecutor exceptionHandleExecutor) {
        this.systemCode = systemCode;
        this.isLast = isLast;
        this.exceptionHandleExecutor = exceptionHandleExecutor;
    }

    /**
     * 构造器
     * @param systemCode
     */
    public GlobalInfo(String systemCode, String systemName) {
        this.systemCode = systemCode;
    }
    
    /**
     * 构造器
     * @param systemCode
     * @param exceptionHandleExecutor
     */
    public GlobalInfo(String systemCode,TaskExecutor exceptionHandleExecutor) {
        this.systemCode = systemCode;
        this.exceptionHandleExecutor = exceptionHandleExecutor;
    }
    
    /**
     * get systemCode
     * 
     * @return Returns the systemCode.<br>
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * set systemCode
     * 
     * @param systemCode The systemCode to set. <br>
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * get isLast
     * 
     * @return Returns the isLast.<br>
     */
    public boolean isLast() {
        return isLast;
    }

    /**
     * set isLast
     * 
     * @param isLast The isLast to set. <br>
     */
    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }

    /** 
     * get exceptionHandleExecutor
     * @return Returns the exceptionHandleExecutor.<br> 
     */
    public TaskExecutor getExceptionHandleExecutor() {
        return exceptionHandleExecutor;
    }

    /** 
     * set exceptionHandleExecutor
     * @param exceptionHandleExecutor The exceptionHandleExecutor to set. <br>
     */
    public void setExceptionHandleExecutor(TaskExecutor exceptionHandleExecutor) {
        this.exceptionHandleExecutor = exceptionHandleExecutor;
    }

}
