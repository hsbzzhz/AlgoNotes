readlist:
1. https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247507941&idx=2&sn=58e7449a3402f15b04e63dc2a2dc339a&chksm=971841eea06fc8f887383661574c3ae4f9cbd5221abf7e14e7640e180e79314a3143b2128848&scene=21#wechat_redirect
2. https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247508928&idx=2&sn=c9bbc75e4d5be8ea23edca7b4a3e70e1&chksm=97183dcba06fb4ddad9b9cf9ef9925febfeb8cfbed205792eea7830c924e1108ed61c149903e&scene=21#wechat_redirect
3. 


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

mysql定位解决慢查询
https://mp.weixin.qq.com/s?__biz=MzI2NzM1OTM4OA==&mid=2247494315&idx=1&sn=22e34f6e9d3a1dd7a8bc922cbbb5aebb&chksm=ea82af58ddf5264e63115092fc927b8eeb6d65ee222172c070aa42fc39450b9188486b1297f4&scene=27
https://blog.csdn.net/my_miuye/article/details/125294804

redis
为啥快：
1. 完全基于内存
2. 整个结构类似于HashMap，查找和操作的复杂度均为O(1)，避免随机IO
3. 。。。。
redis 部署https://blog.csdn.net/qq_26012495/article/details/120299332

redis分布式锁
https://blog.csdn.net/xq_adress/article/details/125474649
Redis 槽数，使用场景啥的

redis 中阻塞的点
https://mp.weixin.qq.com/s?__biz=MzIwNjg4MzY4NA==&mid=2247508977&idx=2&sn=b621f8a424320285cad5aab37f244f92&chksm=97183dfaa06fb4ecea2cb611f97f64de1fc72a1fe9fb197d3e1493d07d6798b5edfa4726509a&scene=21#wechat_redirect

NIO


spring循环依赖

一级缓存中存放完整的bean，这些实例是可以直接使用的
二级缓存里存储的是，实例化以后，但没有设置属性值的bean实例（也就是说bean里的依赖注入还没做）
三级缓存里存的是bean工厂，主要用来生成二级缓存中的原始bean对象

假设BeanA 和 BeanB存在循环依赖，
1. 初始化BeanA，先把BeanA实例化，然后把BeanA包装成 ObjectFactory 对象保存到三级缓存中
2. 接着BeanA 开始对属性中的 BeanB 进行依赖注入，于是开始初始化BeanB，创建BeanB 工厂对象放入到三级缓存中
3. 然后BeanB 对属性中的 BeanA进行依赖注入，在三级缓存中找到BeanA，放入二级缓存中，将不完整的BeanA注入到BeanB中，于是完成 BeanA的依赖注入。
4. BeanB初始化后保存在第一缓存中。BeanA统一拿到BeanN实例，完成初始化。


问题： 如果spring中有两个id相同的bean，会发生什么情况？
1. 在解析转换为 BeanDefinition 的时候，如果发生在同一个xml文件中，会直接报错；如果发生在不通的xml文件中，新的容器会覆盖旧的
2. 但在Spring3中使用@Configuration 注解去声明一个配置类，然后使用@Bean 注解实现bean的声明，这种方式只会保存第一个实例


IOC依赖注入的三种方式
1. Setter方法注入
2. 构造方法注入
3. xml，p标签注入


解决方法， 多个锁场景，对锁进行变成，依次从小到大编号进行加锁。维持加锁顺序~~

antlr

https://blog.csdn.net/u014454538/article/details/129340412
https://blog.csdn.net/u014454538/article/details/129477643
https://blog.csdn.net/u014454538/article/details/129543561

RPC和HTTP
https://blog.csdn.net/gechaoqing/article/details/130435153
