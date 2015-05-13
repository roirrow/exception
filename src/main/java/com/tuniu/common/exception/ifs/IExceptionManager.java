package com.tuniu.common.exception.ifs;

import com.tuniu.common.exception.UnifiedException;
import com.tuniu.common.exception.constant.ExceptionLevel;

/**
 * 
 * <Description>异常管理对外提供api<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public abstract interface IExceptionManager {
    /**
     * 
     * Description:发布异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param level 异常级别 @see com.tuniu.common.exception.constant.ExceptionLevel
     * @param description 异常描述
     * @param businessModule 异常业务模块
     * @param errorCode 异常errorCode
     * @param throwable the throwable which caused this UnifiedException<br>
     */
    public abstract void publish(ExceptionLevel level,String description,String businessModule,String errorCode,Throwable throwable);
    
     /**
      * 
      * Description:发布异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param level 异常级别 @see com.tuniu.common.exception.constant.ExceptionLevel
      * @param description 异常描述
      * @param errorCode 异常errorCode
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publish(ExceptionLevel level,String description,String errorCode,Throwable throwable);
     
     /**
      * 
      * Description: 发布严重级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishSerious(String description,String businessModule,String errorCode,Throwable throwable);
     
     /**
      * 
      * Description: 发布严重级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode<br>
      */
     public abstract void publishSerious(String description,String businessModule,String errorCode);
     
     /**
      * 
      * Description:发布一般级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishCommon(String description,String businessModule,String errorCode,Throwable throwable);
     
     /**
      * 
      * Description:发布一般级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode <br>
      */
     public abstract void publishCommon(String description,String businessModule,String errorCode);
     
     /**
      * 
      * Description:发布轻微级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishSlight(String description,String businessModule,String errorCode,Throwable throwable);
     
     /**
      * 
      * Description:发布轻微级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param description 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode<br>
      */
     public abstract void publishSlight(String description,String businessModule,String errorCode);
     
     /**
      * 
      * Description:发布异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param ue 统一的异常 @see com.tuniu.common.exception.UnifiedException
      */
     public abstract void publish(UnifiedException ue);
     
     /**
      * 
      * Description: 发布并抛出异常,如果希望处理异常并且希望异常能够中止当前流程，可以使用此方法<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param ue 统一的异常 @see com.tuniu.common.exception.UnifiedException
      * @throws UnifiedException <br>
      */
     public abstract void publishAndThrow(UnifiedException ue) throws UnifiedException;
}
