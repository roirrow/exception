package com.tuniu.common.exception.handler.repository;
import javax.persistence.Id;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * <Description>mongo repository<br> 
 *  
 * @author wanglei<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2015年4月13日 <br>
 */
public interface ExceptionHandlerRepository extends MongoRepository<ExceptionHandlerRepository.ExceptionRecord, String>{

    /**
     * 
     * <Description>用来记录mongo的内部domain类<br> 
     *  
     * @author wanglei<br>
     * @version 1.0<br>
     * @taskId <br>
     * @CreateDate 2015年4月20日 <br>
     */
    class ExceptionRecord{
        @Id
        private String id;
        private String systemCode;
        private String systemName;
        private String level;
        private String description;
        private String errorCode;
        private String exception;
        /**
         * 构造器
         * @param systemCode
         * @param systemName
         * @param level
         * @param description
         * @param errorCode
         * @param exception
         */
        public ExceptionRecord(String systemCode, String systemName, String level, String description, String errorCode,String exception) {
            super();
            this.systemCode = systemCode;
            this.systemName = systemName;
            this.level = level;
            this.description = description;
            this.errorCode = errorCode;
            this.exception = exception;
        }
        
        /** 
         * get id
         * @return Returns the id.<br> 
         */
        public String getId() {
            return id;
        }
        /** 
         * set id
         * @param id The id to set. <br>
         */
        public void setId(String id) {
            this.id = id;
        }
        /** 
         * get systemCode
         * @return Returns the systemCode.<br> 
         */
        public String getSystemCode() {
            return systemCode;
        }
        /** 
         * set systemCode
         * @param systemCode The systemCode to set. <br>
         */
        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }
        /** 
         * get systemName
         * @return Returns the systemName.<br> 
         */
        public String getSystemName() {
            return systemName;
        }
        /** 
         * set systemName
         * @param systemName The systemName to set. <br>
         */
        public void setSystemName(String systemName) {
            this.systemName = systemName;
        }
        /** 
         * get level
         * @return Returns the level.<br> 
         */
        public String getLevel() {
            return level;
        }
        /** 
         * set level
         * @param level The level to set. <br>
         */
        public void setLevel(String level) {
            this.level = level;
        }
        /** 
         * get description
         * @return Returns the description.<br> 
         */
        public String getDescription() {
            return description;
        }
        /** 
         * set description
         * @param description The description to set. <br>
         */
        public void setDescription(String description) {
            this.description = description;
        }
        /** 
         * get exception
         * @return Returns the exception.<br> 
         */
        public String getException() {
            return exception;
        }
        /** 
         * set exception
         * @param exception The exception to set. <br>
         */
        public void setException(String exception) {
            this.exception = exception;
        }

        /** 
         * get errorCode
         * @return Returns the errorCode.<br> 
         */
        public String getErrorCode() {
            return errorCode;
        }

        /** 
         * set errorCode
         * @param errorCode The errorCode to set. <br>
         */
        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
        
    }
}
