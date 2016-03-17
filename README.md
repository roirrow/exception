unified exception framework（统一的异常框架）
config目录下是相关配置文件，如果使用了spring的话，请把相关配置import到spring的上下文配置文件里,相关占位符${}需要设置具体的值。

exception.xml 异常处理的主要配置：

    <!-- responsibility chain for exception handle -->
	<bean id="exceptionHandlerChain"
		class="ExceptionHandlerChain">
		<property name="chain">
			<list>
				<bean id="logBackExceptionHandler"
					class="LogBackExceptionHandler">
					<property name="level" value="3" />
				</bean>
				<!--如果配置了mongodb异常处理resolver,则需要配置mongodb-context.xml,否则不需要-->
				<bean id="mongoDBExceptionHandler"
					class="MongoDBExceptionHandler">
					<property name="level" value="2" />
					<property name="asynFlag" value="true" /property>
				</bean>
			</list>
		</property>
	</bean>
	
    <!-- thread pool for asyn handle -->
    <!--如果异常处理resolver没有设置异步处理(asynFlag=true的)的话，不需要配置exceptionHandleExecutor-->
    
    <bean id="exceptionHandleExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
		<property name="threadNamePrefix" value="exceptionHandleExecutor" />
		<property name="corePoolSize" value="20" />
		<property name="maxPoolSize" value="20" />
		<property name="queueCapacity" value="100000" />
	</bean>
	
	<!-- exception manager -->
	<bean id="exceptionManager" class="ExceptionManager">
		<property name="systemCode" value="YOUR SYSTEM CODE" />
		<property name="ehc" ref="exceptionHandlerChain" />
		<property name="exceptionHandleExecutor" ref="exceptionHandleExecutor"/>
	</bean>
	
	<!--切面配置，建议是将切面加在最上层,比如我这里是Controller-->
    <!-- aop config -->
	<aop:config>
		<aop:pointcut id="unifiedException"
			expression="execution(* com..*Controller.*(..))" />
		<aop:advisor advice-ref="unifiedExceptionThrowsAdvice"
			pointcut-ref="unifiedException" />
	</aop:config>
	<!-- throw advice -->
	<bean id="unifiedExceptionThrowsAdvice"
		class="UnifiedExceptionThrowsAdvice">
		<property name="exceptionManager" ref="exceptionManager" />
	</bean>

mongodb-contexnt.xml 记录异常日志到mongodb的配置:

    <!-- Connection to MongoDB server -->
    <mongo:db-factory host="${mongo.host}" port="${mongo.port}" dbname="${mongo.dbname}" username="${mongo.user}" password="${mongo.password}" />
    
    <!-- MongoDB Template -->
    <bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
        <constructor-arg name="mongoDbFactory" ref="mongoDbFactory"/>
    </bean>

    <!-- mongo repositories -->
    <mongo:repositories base-package="com.roirrow.common.exception.handler.repository" />
