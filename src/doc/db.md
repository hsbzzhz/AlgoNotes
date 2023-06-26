
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


mysql定位解决慢查询
https://mp.weixin.qq.com/s?__biz=MzI2NzM1OTM4OA==&mid=2247494315&idx=1&sn=22e34f6e9d3a1dd7a8bc922cbbb5aebb&chksm=ea82af58ddf5264e63115092fc927b8eeb6d65ee222172c070aa42fc39450b9188486b1297f4&scene=27
https://blog.csdn.net/my_miuye/article/details/125294804

redis分布式锁
https://blog.csdn.net/xq_adress/article/details/125474649
Redis 槽数，使用场景啥的

sql三大范式 https://blog.csdn.net/yiZzhu/article/details/121616375
第一范式，每列都是不可分割，比如有个字段叫学校信息，这违背了第一范式
第二范式，在第一范式的基础上。每列都跟主键相关
第三范式，在第二范式的的基础上。每列只能跟主键有直接关系，比如设备号的pid，这样设计就不是第三范式。


java threadlocal 线程隔离
https://blog.csdn.net/u010445301/article/details/111322569
使用场景：
spring中使用threadlocal 确保线程安全。确保有状态的bean能够以singleton方式在多线程中正常工作。

kafka 
https://blog.csdn.net/m0_65931372/article/details/125971395
面试问题

NIO
