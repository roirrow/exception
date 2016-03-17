/**
 * Alibaba.com Inc.
 * Copyright (c) 1999-2016 All Rights Reserved.
 */
package com.roirrow.common.exception;

import com.roirrow.common.exception.constant.ExceptionLevel;
import com.roirrow.common.exception.vo.ErrorDefinition;

import java.util.Map;

/**
 * 统一异常util
 *
 * @author roirrowwang.wl
 * @version $Id: UnifiedExceptionUtil.java, v 0.1 16/1/11 下午7:14 roirrowwang.wl Exp $$
 */
public class UnifiedExceptionUtil {

    /**
     * 抛出轻微等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwSlightException(ErrorDefinition errorDefinition, String businessModule,
                                            Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SLIGHT, errorDefinition.getErrorMessage(),
                                   errorDefinition.getErrorCode(), businessModule, context, cause);
    }

    /**
     * 抛出一般等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwCommonException(ErrorDefinition errorDefinition, String businessModule,
                                            Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.COMMON, errorDefinition.getErrorMessage(),
                                   errorDefinition.getErrorCode(), businessModule, context, cause);
    }

    /**
     * 抛出严重等级异常
     *
     * @param errorDefinition 必填，异常错误message、错误code
     * @param businessModule  必填，异常所属模块
     * @param context         选填，异常上下文，可以设置业务关键字
     * @param cause           选填
     * @throws UnifiedException
     */
    public static void throwSeriousException(ErrorDefinition errorDefinition, String businessModule,
                                             Map<String, Object> context, Throwable cause) throws UnifiedException {
        throw new UnifiedException(ExceptionLevel.SERIOUS, errorDefinition.getErrorMessage(),
                                   errorDefinition.getErrorCode(), businessModule, context, cause);
    }
}
