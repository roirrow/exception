package com.tuniu.common.exception.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.api.ExceptionManager;
/**
 * 
 * <Description>切面统一异常处理,仅处理UnifiedException,若使用该切面配置，请在业务代码里throw出UnifiedException<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月20日 <br>
 */
public class UnifiedExceptionThrowsAdvice implements ThrowsAdvice {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(UnifiedExceptionThrowsAdvice.class);
    /**
     * 异常处理器
     */
    private ExceptionManager exceptionManager;
    /**
     * 
     * Description:对于统一异常的处理<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param method
     * @param args
     * @param target
     * @param ex
     * @throws Throwable <br>
     */
    public void afterThrowing(Method method, Object[] args, Object target,
            Exception ex) throws Throwable {
        if(ex instanceof UnifiedException) {
            UnifiedException ue = (UnifiedException)ex;
            exceptionManager.publish(ue);
        } else {
            logger.error("unkown exception", ex);
        }
    }
    /** 
     * get exceptionManager
     * @return Returns the exceptionManager.<br> 
     */
    public ExceptionManager getExceptionManager() {
        return exceptionManager;
    }
    /** 
     * set exceptionManager
     * @param exceptionManager The exceptionManager to set. <br>
     */
    public void setExceptionManager(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }
    
}
