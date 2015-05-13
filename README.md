unified exception framework（统一的异常框架）
config目录下是相关配置文件，如果使用了spring的话，请把这些配置import到spring的上下文配置文件里：
1.exception.xml 异常处理的主要配置；
2.memcached.xml 通知模块用来缓存通知人id用到了memcache；
3.common-utils.xml 通知组件配置；
4.mongodb-contexnt.xml 记录异常日志到mongodb的配置；
相关占位符${}需要设置具体的值。