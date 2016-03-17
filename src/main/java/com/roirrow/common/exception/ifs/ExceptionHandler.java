package com.roirrow.common.exception.ifs;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.handler.ExceptionHandlerChain;
import com.roirrow.common.exception.vo.GlobalInfo;

/**
 * 
 * <Description>异常处理接口<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public interface ExceptionHandler extends HandlerSupport{

    /**
     * 
     * Description:处理异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue 统一异常
     * @param gi 异常处理全局信息
     * @param ehc 异常处理责任链<br>
     */
    public void handle(UnifiedException ue,GlobalInfo gi,ExceptionHandlerChain ehc);
}
