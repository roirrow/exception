package com.tuniu.common.exception.api;

import org.springframework.core.task.TaskExecutor;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.constant.ExceptionLevel;
import com.tuniu.common.exception.handler.ExceptionHandlerChain;
import com.tuniu.common.exception.ifs.IExceptionManager;
import com.tuniu.common.exception.vo.GlobalInfo;

/**
 * 
 * <Description>统一异常管理器，异常处理都是异步进行的，需要配置异常处理线程池 @see org.springframework.core.task.TaskExecutor
 * 使用统一的异常定义 @see com.tuniu.common.exception.UnifiedException
 * 异常的处理以责任链的方式进行 @see com.tuniu.common.exception.handler.ExceptionHandlerChain
 * 可以自定义异常处理类 @see com.tuniu.common.exception.handler.AbsExceptionHandler
 * <br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class ExceptionManager implements IExceptionManager{

    /**
     * 系统三字码
     */
    private String systemCode = "未知";
    
    /**
     * 系统中文名
     */
    private String systemName = "未知";
    
    /**
     * 异常处理链
     */
    private ExceptionHandlerChain ehc;
    
    /**
     * 异步处理线程池
     */
    private TaskExecutor exceptionHandleExecutor;
    
    @Override
    public void publish(ExceptionLevel level, String description, String businessModule, String errorCode, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(level, description,businessModule,errorCode,throwable);
        accept(ue, gi);
    }
    
    @Override
    public void publish(ExceptionLevel level,String description,String errorCode,Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(level, description,errorCode,throwable);
        accept(ue, gi);
    }

    @Override
    public void publishSerious(String description, String businessModule, String errorCode, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SERIOUS, description, businessModule, errorCode, throwable);
        accept(ue,gi);
    }

    @Override
    public void publishSerious(String description, String businessModule, String errorCode) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SERIOUS, description, businessModule, errorCode,null);
        accept(ue,gi);
    }

    @Override
    public void publishCommon(String description, String businessModule, String errorCode, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.COMMON, description, businessModule, errorCode, throwable);
        accept(ue,gi);
    }

    @Override
    public void publishCommon(String description, String businessModule, String errorCode) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.COMMON, description, businessModule, errorCode, null);
        accept(ue,gi);
    }

    @Override
    public void publishSlight(String description, String businessModule, String errorCode, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SLIGHT, description, businessModule, errorCode, throwable);
        accept(ue,gi);    
    }

    @Override
    public void publishSlight(String description, String businessModule, String errorCode) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SLIGHT, description, businessModule, errorCode, null);
        accept(ue,gi);
    }
    
    @Override
    public void publish(UnifiedException ue) {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        accept(ue, gi);
    }
    
    @Override
    public void publishAndThrow(UnifiedException ue) throws UnifiedException {
        GlobalInfo gi = new GlobalInfo(systemCode, systemName,exceptionHandleExecutor);
        accept(ue, gi);
        throw ue;
    }

    /**
     * 
     * Description:接受并处理异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue 统一异常
     * @param gi 异常模块全局信息
     * @return <br>
     */
    protected void accept(UnifiedException ue, GlobalInfo gi) {
        //如果已经处理过，则不继续处理
        if(!ue.isHandled()) {
            ehc.handleException(ue, gi);
            ue.setHandled(true);
        }
    }
    
    /** 
     * get systemCode
     * @return Returns the systemCode.<br> 
     */
    public String getSystemCode() {
        return systemCode;
    }

    /** 
     * set systemCode
     * @param systemCode The systemCode to set. <br>
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /** 
     * get systemName
     * @return Returns the systemName.<br> 
     */
    public String getSystemName() {
        return systemName;
    }

    /** 
     * set systemName
     * @param systemName The systemName to set. <br>
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /** 
     * get ehc
     * @return Returns the ehc.<br> 
     */
    public ExceptionHandlerChain getEhc() {
        return ehc;
    }

    /** 
     * set ehc
     * @param ehc The ehc to set. <br>
     */
    public void setEhc(ExceptionHandlerChain ehc) {
        this.ehc = ehc;
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
