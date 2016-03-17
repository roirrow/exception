package com.roirrow.common.exception.api;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.constant.ExceptionLevel;
import com.roirrow.common.exception.handler.ExceptionHandlerChain;
import com.roirrow.common.exception.ifs.IExceptionManager;
import com.roirrow.common.exception.vo.GlobalInfo;
import org.springframework.core.task.TaskExecutor;

import java.util.Map;

/**
 *
 * <Description>统一异常管理器，异常处理都是异步进行的，需要配置异常处理线程池 @see org.springframework.core.task.TaskExecutor
 * 使用统一的异常定义
 * @see com.roirrow.common.exception.UnifiedException
 * 异常的处理以责任链的方式进行
 * @see com.roirrow.common.exception.handler.ExceptionHandlerChain
 * 可以自定义异常处理类
 * @see com.roirrow.common.exception.handler.AbsExceptionHandler
 * <br>
 *
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class ExceptionManager implements IExceptionManager {

    /**
     * 系统三字码
     */
    private String systemCode = "未知";

    /**
     * 异常处理链
     */
    private ExceptionHandlerChain ehc;

    /**
     * 异步处理线程池
     */
    private TaskExecutor exceptionHandleExecutor;

    @Override
    public void publish(ExceptionLevel level, String errorMessage, String businessModule, String errorCode, Map<String,Object> context,Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(level, errorMessage,businessModule,errorCode,context,throwable);
        accept(ue, gi);
    }


    @Override
    public void publishSerious(String errorMessage, String businessModule, String errorCode, Map<String,Object> context,Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SERIOUS, errorMessage, businessModule, errorCode, context,throwable);
        accept(ue,gi);
    }



    @Override
    public void publishCommon(String errorMessage, String businessModule, String errorCode,Map<String,Object> context, Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.COMMON, errorMessage, businessModule, errorCode, context,throwable);
        accept(ue,gi);
    }



    @Override
    public void publishSlight(String errorMessage, String businessModule, String errorCode, Map<String,Object> context,Throwable throwable) {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
        UnifiedException ue = new UnifiedException(ExceptionLevel.SLIGHT, errorMessage, businessModule, errorCode,context, throwable);
        accept(ue,gi);
    }


    @Override
    public void publish(UnifiedException ue) {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
        accept(ue, gi);
    }

    @Override
    public void publishAndThrow(UnifiedException ue) throws UnifiedException {
        GlobalInfo gi = new GlobalInfo(systemCode,exceptionHandleExecutor);
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
