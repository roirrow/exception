package com.roirrow.common.exception.handler;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.handler.repository.ExceptionHandlerRepository;
import com.roirrow.common.exception.vo.GlobalInfo;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
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
    //支持的异常等级 @see ExceptionLevel
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
        ExceptionHandlerRepository.ExceptionRecord er = new ExceptionHandlerRepository.ExceptionRecord(gi.getSystemCode(), ue
                .getLevel().getDescription(), ue.getErrorMessage(), ue.getErrorCode(), exception);
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
