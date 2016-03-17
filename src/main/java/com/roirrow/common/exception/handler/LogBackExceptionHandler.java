package com.roirrow.common.exception.handler;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.constant.ExceptionLevel;
import com.roirrow.common.exception.vo.GlobalInfo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
    
    /**支持的异常等级
    @see ExceptionLevel
    **/
    private int level = 3;
    
    @Override
    public boolean supports(UnifiedException ue) {
        return ue.getLevel().getValue() <= level;
    }

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        Map<String,Object> context = ue.getContext();
        String errorContext = null == context ? "" : JSONObject.valueToString(context);
        LOG.error(
                "system:" + gi.getSystemCode() + " module" + ue.getBusinessModule() + " error:"
                        + ue.getErrorMessage() + " context:" + errorContext, ue);
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
