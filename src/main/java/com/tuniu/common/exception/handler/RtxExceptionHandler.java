package com.tuniu.common.exception.handler;

import javax.annotation.Resource;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.vo.GlobalInfo;
import com.tuniu.common.notify.service.NotifyService;

/**
 * <Description>rtx异常处理器<br>
 * 
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class RtxExceptionHandler extends AbsExceptionHandler {
    //支持的异常等级 @see com.tuniu.common.exception.constant.ExceptionLevel
    private int level = 1;
    //rtx send url
    private String rtxUrl = "http://rtx.tuniu.org/rtx/sms/sendRtx";
    @Resource
    private NotifyService notifyService;

    @Override
    public boolean supports(UnifiedException ue) {
        if (ue.getLevel().getValue() <= level ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doHandle(UnifiedException ue, GlobalInfo gi) {
        // use rtx send users configed in db
        // we only have rtx product environment,so here we write like this
        notifyService.notifyRTX("异常提醒", "系统code:" + gi.getSystemCode() + " 系统名称:" + gi.getSystemName() + " 业务模块:" + ue.getBusinessModule()
                + " 发生异常,异常描述:"
                + ue.getDescription(), null, rtxUrl);
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

    /** 
     * get rtxUrl
     * @return Returns the rtxUrl.<br> 
     */
    public String getRtxUrl() {
        return rtxUrl;
    }

    /** 
     * set rtxUrl
     * @param rtxUrl The rtxUrl to set. <br>
     */
    public void setRtxUrl(String rtxUrl) {
        this.rtxUrl = rtxUrl;
    }

}
