package com.roirrow.common.exception.handler;

import java.util.LinkedList;
import java.util.List;

import com.roirrow.common.exception.UnifiedException;
import com.roirrow.common.exception.vo.GlobalInfo;

/**
 * 
 * <Description>异常处理责任链<br> 
 * @see AbsExceptionHandler
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public class ExceptionHandlerChain {
    /**
     * 异常处理类数组
     */
    private List<AbsExceptionHandler> chain = new LinkedList<AbsExceptionHandler>();
    
    /**
     * 当前下标
     */
    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };
    
    /**
     * 
     * Description:循环处理异常<br> 
     *  
     * @author wanglei<br>
     * @taskId <br>
     * @param ue
     * @param gi
     * @return <br>
     */
    public boolean handleException(UnifiedException ue,GlobalInfo gi) {
        Integer currentIndex = index.get();
        //Determine if this is the last exception handler
        boolean isLast = false;
        if(currentIndex < chain.size()) {
            if(currentIndex == chain.size() -1) {
                isLast = true;
                index.remove();
            } else {
                index.set(currentIndex + 1);
            }
            gi.setLast(isLast);
            chain.get(currentIndex).handle(ue, gi,this);
        }
        return true;
    }

    /** 
     * get chain
     * @return Returns the chain.<br> 
     */
    public List<AbsExceptionHandler> getChain() {
        return chain;
    }

    /** 
     * set chain
     * @param chain The chain to set. <br>
     */
    public void setChain(List<AbsExceptionHandler> chain) {
        this.chain = chain;
    }
    
}
