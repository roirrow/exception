/**
 * Alibaba.com Inc.
 * Copyright (c) 1999-2016 All Rights Reserved.
 */
package com.roirrow.common.exception.vo;

/**
 * 错误信息抽象,需要自己定义这个接口的实现，用于管理错误的errorCode和errorMessage。比如直接用一个Enum类管理
 * @author roirrowwang.wl
 * @version $Id: ErrorDefinition.java, v 0.1 16/1/11 下午7:23 roirrowwang.wl Exp $$
 */
public interface ErrorDefinition {

    /**
     * 获取错误的errorCode
     * @return
     */
    public String getErrorCode();

    /**
     * 获取错误的errorMessage
     * @return
     */
    public String getErrorMessage();

}
