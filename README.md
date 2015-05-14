unified exception framework（统一的异常框架）
config目录下是相关配置文件，如果使用了spring的话，请把相关配置import到spring的上下文配置文件里,相关占位符${}需要设置具体的值。

exception.xml 异常处理的主要配置：

    <!-- responsibility chain for exception handle -->
	<bean id="exceptionHandlerChain"
		class="com.tuniu.common.exception.handler.ExceptionHandlerChain">
		<property name="chain">
			<list>
				<bean id="logBackExceptionHandler"
					class="com.tuniu.common.exception.handler.LogBackExceptionHandler">
					<property name="level" value="3" />
				</bean>
				<!--如果配置了mongodb异常处理resolver,则需要配置mongodb-context.xml,否则不需要-->
				<bean id="mongoDBExceptionHandler"
					class="com.tuniu.common.exception.handler.MongoDBExceptionHandler">
					<property name="level" value="2" />
					<property name="asynFlag" value="true" /property>
				</bean>
				<!--如果配置了rtx通知异常处理，则需要配置common-utils.xml，负责不需要-->
				<bean id="rtxExceptionHandler"
					class="com.tuniu.common.exception.handler.RtxExceptionHandler">
					<property name="level" value="1" />
					<property name="asynFlag" value="true" /property>
				</bean>
			</list>
		</property>
	</bean>
	
    <!-- thread pool for asyn handle -->
    <!--如果异常处理resolver没有异步处理(asynFlag=true的)的话，不需要配置exceptionHandleExecutor-->
    
    <bean id="exceptionHandleExecutor"
		class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
		<property name="threadNamePrefix" value="exceptionHandleExecutor" />
		<property name="corePoolSize" value="20" />
		<property name="maxPoolSize" value="20" />
		<property name="queueCapacity" value="100000" />
	</bean>
	
	<!-- exception manager -->
	<bean id="exceptionManager" class="com.tuniu.common.exception.api.ExceptionManager">
		<property name="systemCode" value="CFM" />
		<property name="systemName" value="New Confirmation" />
		<property name="ehc" ref="exceptionHandlerChain" />
		<property name="exceptionHandleExecutor" ref="exceptionHandleExecutor"/>
	</bean>
	
	<!--切面配置，建议是将切面加在最上层-->
    <!-- aop config -->
	<aop:config>
		<aop:pointcut id="unifiedException"
			expression="execution(* com.tuniu.mauritius..*Controller.*(..)) or execution(* com.tuniu.mauritius..*Delegate.*(..))" />
		<aop:advisor advice-ref="unifiedExceptionThrowsAdvice"
			pointcut-ref="unifiedException" />
	</aop:config>
	<!-- throw advice -->
	<bean id="unifiedExceptionThrowsAdvice"
		class="com.tuniu.common.exception.aop.UnifiedExceptionThrowsAdvice">
		<property name="exceptionManager" ref="exceptionManager" />
	</bean>

common-utils.xml 通知组件配置,通知人的获取逻辑是从数据库获取，缓存在memcache里：

	<bean id="defaultTranscoderTool" class="net.spy.memcached.transcoders.SerializingTranscoder"></bean>
	
	<!-- use memcache to cache rtx send users -->
	<bean id="memberCacheUtil" class="com.tuniu.common.utils.usercache.MemberCacheUtil">
		<property name="servers">
			<value>${memcache.address}</value>
		</property>
		<property name="timeOut">
			<value>${memcache.timeout}</value>
		</property>
		<property name="expiration">
			<value>${memcache.expiration}</value>
		</property>
		<property name="asyncget">
			<value>${memcache.asyncget}</value>
		</property>
		<property name="compression">
			<value>${memcache.compression}</value>
		</property>
		<property name="transcoder">
			<ref bean="defaultTranscoderTool" />
		</property>
	</bean>
	
	<!-- scan beans regitered by annotation -->
	<context:component-scan base-package="com.tuniu.common" />
	
	<!-- mybatis dao used to query rtx send users from db -->
	<bean id="userDao" class="org.mybatis.spring.mapper.MapperFactoryBean">
		<property name="mapperInterface" value="com.tuniu.common.notify.dao.UserDao"></property>
		<property name="sqlSessionFactory" ref="cfmSqlSessionFactory"></property>
	</bean>

如果使用mysql存储通知人配置，那么sql脚本为：

CREATE TABLE `notify_users_config` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_type` int(4) NOT NULL COMMENT '用户类型 1:RTX  2:EMAIL  3:SMS',
    `address` varchar(1024) NOT NULL COMMENT '用户地址，如果是RTX为用户UID，EMAIL为用户邮箱，短信为用户手机号',
    `del_flag` int(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记 0：未删除有效  1：已删除',
    PRIMARY KEY (`id`)
)  ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

e.g:

INSERT INTO `notify_users_config` (`id`,`user_type`,`address`,`del_flag`) VALUES (1,1,'?',0);

mongodb-contexnt.xml 记录异常日志到mongodb的配置:

	<bean id="appProperties" class="com.tuniu.mauritius.confirmation.AppProperties">
		<property name="currentProfile" value="${env.name}"></property>
	</bean>

    <!-- Connection to MongoDB server -->
    <mongo:db-factory host="${mongo.host}" port="${mongo.port}" dbname="${mongo.dbname}" username="${mongo.user}" password="${mongo.password}" />
    
    <!-- MongoDB Template -->
    <bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
        <constructor-arg name="mongoDbFactory" ref="mongoDbFactory"/>
    </bean>

    <!-- Package w/ automagic repositories -->
    <mongo:repositories base-package="com.tuniu.mauritius.confirmation,com.tuniu.common.exception.handler" />
