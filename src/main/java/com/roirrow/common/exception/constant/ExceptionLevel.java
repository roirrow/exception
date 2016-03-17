package com.roirrow.common.exception.constant;

/**
 * <Description>异常等级 1： 严重 2：一般 3: 非重要异常 值越大表示级别越低,默认是非重要异常<br>
 * 
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public enum ExceptionLevel {
    SERIOUS(1, "严重"),
    COMMON(2, "一般"),
    SLIGHT(3, "轻微");

    private int value;

    private String description;

    private ExceptionLevel(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * get value
     * 
     * @return Returns the value.<br>
     */
    public int getValue() {
        return value;
    }

    /**
     * set value
     * 
     * @param value The value to set. <br>
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * get description
     * 
     * @return Returns the description.<br>
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     * 
     * @param description The description to set. <br>
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
