package com.tuniu.common.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.vo.GlobalInfo;

/**
 * <Description>打印logback日志文件<br>
 * 
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class LogBackExceptionHandler extends AbsExceptionHandler {
    // logger
    private Logger LOG = LoggerFactory.getLogger(LogBackExceptionHandler.class);
    
    //支持的异常等级 @see com.tuniu.common.exception.constant.ExceptionLevel
    private int level = 3;
    
    @Override
    public boolean supports(UnifiedException ue) {
        return ue.getLevel().getValue() <= level;
    }

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        LOG.error(
                "系统code:" + gi.getSystemCode() + " 系统名称:" + gi.getSystemName() + " 业务模块:" + ue.getBusinessModule() + " 发生异常,异常描述:"
                        + ue.getDescription(), ue);
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
