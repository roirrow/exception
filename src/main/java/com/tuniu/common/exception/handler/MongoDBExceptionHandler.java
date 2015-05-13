package com.tuniu.common.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.handler.repository.ExceptionHandlerRepository;
import com.tuniu.common.exception.vo.GlobalInfo;
/**
 * 
 * <Description>mongo db异常处理器<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class MongoDBExceptionHandler extends AbsExceptionHandler{
    //支持的异常等级 @see com.tuniu.common.exception.constant.ExceptionLevel
    private int level = 2;
    
    @Resource
    private ExceptionHandlerRepository exceptionHandlerRepository;
    
    @Override
    public boolean supports(UnifiedException ue) {
        if (ue.getLevel().getValue() <= level) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ue.printStackTrace(printWriter);
        String exception = ue.getMessage() + stringWriter.toString();
        ExceptionHandlerRepository.ExceptionRecord er = new ExceptionHandlerRepository.ExceptionRecord(gi.getSystemCode(), gi.getSystemName(), ue
                .getLevel().getDescription(), ue.getDescription(), ue.getErrorCode(), exception);
        exceptionHandlerRepository.save(er);
    }

    /** 
     * get level
     * @return Returns the level.<br> 
     */
    public int getLevel() {
        return level;
    }

    /** 
     * set level
     * @param level The level to set. <br>
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
