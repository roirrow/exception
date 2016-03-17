package com.roirrow.common.exception.ifs;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.constant.ExceptionLevel;

import java.util.Map;

/**
 * 
 * <errorMessage>异常管理对外提供api<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public abstract interface IExceptionManager {
    /**
     * 
     * errorMessage:发布异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param level 异常级别
     * @see ExceptionLevel
     * @param errorMessage 异常描述
     * @param businessModule 异常业务模块
     * @param errorCode 异常errorCode
     * @param context  异常上下文参数
     * @param throwable the throwable which caused this UnifiedException<br>
     */
    public abstract void publish(ExceptionLevel level,String errorMessage,String businessModule,String errorCode,Map<String,Object> context,Throwable throwable);
    
     /**
      * 
      * errorMessage: 发布严重级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param errorMessage 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param context  异常上下文参数
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishSerious(String errorMessage,String businessModule,String errorCode,Map<String,Object> context,Throwable throwable);
     
     /**
      * 
      * errorMessage:发布一般级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param errorMessage 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param context  异常上下文参数
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishCommon(String errorMessage,String businessModule,String errorCode,Map<String,Object> context,Throwable throwable);
     
     /**
      * 
      * errorMessage:发布轻微级别的异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param errorMessage 异常描述
      * @param businessModule 异常业务模块
      * @param errorCode 异常errorCode
      * @param context  异常上下文参数
      * @param throwable the throwable which caused this UnifiedException<br>
      */
     public abstract void publishSlight(String errorMessage,String businessModule,String errorCode,Map<String,Object> context,Throwable throwable);
     

     /**
      * 
      * errorMessage:发布异常<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param ue 统一的异常
      * @see UnifiedException
      */
     public abstract void publish(UnifiedException ue);
     
     /**
      * 
      * errorMessage: 发布并抛出异常,如果希望处理异常并且希望异常能够中止当前流程，可以使用此方法<br> 
      *  
      * @author wanglei<br>
      * @taskId <br>
      * @param ue 统一的异常 @see UnifiedException
      * @throws UnifiedException <br>
      */
     public abstract void publishAndThrow(UnifiedException ue) throws UnifiedException;
}
