https://www.yuque.com/wukong-zorrm/qdoy5p/zwre52
readinglist:
1. https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247507941&idx=2&sn=58e7449a3402f15b04e63dc2a2dc339a&chksm=971841eea06fc8f887383661574c3ae4f9cbd5221abf7e14e7640e180e79314a3143b2128848&scene=21#wechat_redirect
2. https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247508928&idx=2&sn=c9bbc75e4d5be8ea23edca7b4a3e70e1&chksm=97183dcba06fb4ddad9b9cf9ef9925febfeb8cfbed205792eea7830c924e1108ed61c149903e&scene=21#wechat_redirect


Reactive Programming 作为观察者模式（Observer） 的延伸，不同于传统的命令编程方式（ Imperative programming）同步拉取数据的方式，如迭代器模式（Iterator） 。而是采用数据发布者同步或异步地推送到数据流（Data Streams）的方案。当该数据流（Data Steams）订阅者监听到传播变化时，立即作出响应动作。在实现层面上，Reactive Programming 可结合函数式编程简化面向对象语言语法的臃肿性，屏蔽并发实现的复杂细节，提供数据流的有序操作，从而达到提升代码的可读性，以及减少 Bugs 出现的目的。同时，Reactive Programming 结合背压（Backpressure）的技术解决发布端生成数据的速率高于订阅端消费的问题。


目前springboot版本是 2.7；更新到3.0版本


serviceComb 介绍： https://blog.csdn.net/Java_Pluto/article/details/115271722
开箱即用

服务中心：CSE 提供服务注册，发现
服务治理：基于Rbibbon的负载均衡方案
网关： zuul，提供限流策略

提供cse接口：
@RestSchema(schemaId = "metaone-ui");
调用cse接口：
Objectres = resTemplate.getForObject(uri, Object.class);


熔断容错

配置微服务datafactory 的 yaml

### serviceComb服务治理方案
- 在microservice.yaml 配置文件中加入治理策略
  - 负载均衡：
  - 限流策略：基于zuul网关实现：限制单位时间内的访问次数
    - 添加application.yml文件配置
    - 添加zuul网关启动类
  - 熔断机制：
    - 配置请求容量阈值
    - 如果服务抛出异常，请求多次将不会再直接访问服务，等服务恢复后可再次重新访问

redis
redis分布式锁
https://blog.csdn.net/xq_adress/article/details/125474649
Redis 槽数，使用场景啥的

redis 中阻塞的点
https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247508977&idx=2&sn=b621f8a424320285cad5aab37f244f92&chksm=97183dfaa06fb4ecea2cb611f97f64de1fc72a1fe9fb197d3e1493d07d6798b5edfa4726509a&scene=21#wechat_redirect

NIO


spring循环依赖

问题： 如果spring中有两个id相同的bean，会发生什么情况？
1. 在解析转换为 BeanDefinition 的时候，如果发生在同一个xml文件中，会直接报错；如果发生在不通的xml文件中，新的容器会覆盖旧的
2. 但在Spring3中使用@Configuration 注解去声明一个配置类，然后使用@Bean 注解实现bean的声明，这种方式只会保存第一个实例


antlr

https://blog.csdn.net/u014454538/article/details/129340412
https://blog.csdn.net/u014454538/article/details/129477643
https://blog.csdn.net/u014454538/article/details/129543561
