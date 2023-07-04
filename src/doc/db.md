
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

redis
为啥快：
1. 完全基于内存
2. 整个结构类似于HashMap，查找和操作的复杂度均为O(1)，避免随机IO
3. 。。。。
redis 部署https://blog.csdn.net/qq_26012495/article/details/120299332

redis分布式锁
https://blog.csdn.net/xq_adress/article/details/125474649
Redis 槽数，使用场景啥的

~~sql三大范式 https://blog.csdn.net/yiZzhu/article/details/121616375
第一范式，每列都是不可分割，比如有个字段叫学校信息，这违背了第一范式
第二范式，在第一范式的基础上。每列都跟主键相关
第三范式，在第二范式的的基础上。每列只能跟主键有直接关系，比如设备号的pid，这样设计就不是第三范式。~~

线程池
https://blog.csdn.net/zxy5663/article/details/126471569

java threadlocal 线程隔离
https://blog.csdn.net/u010445301/article/details/111322569
使用场景：
spring中使用threadlocal 确保线程安全。确保有状态的bean能够以singleton方式在多线程中正常工作。

~~kafka~~ 
https://blog.csdn.net/m0_65931372/article/details/125971395

https://blog.csdn.net/roykingw/article/details/126611149
一个topic 下有多个partition是，分别放在不同的broker中。
数据内容放在了.log文件中，offset 放在了 .index文件中

消费组中有多个consumer， 每个consumer对应一个partition。
也就是：一个消费组，可以并行消费一个topic

性能为啥好
1. 顺序写，虽然kafka也是把数据写入磁盘，但是用的是顺序写，追加数据追加到末尾。随机写的话实在文件某个位置修改数据
2. 零拷贝，Kafka 利用了 Linux 的 sendFile 技术（NIO），省去了进程切换和一次数据拷贝，让性能变得更好
3. partition 下 分区存储log文件，检索快，读取快。每个log文件不超过1g

保证消息不丢失
1. 生产端发生丢失 - 网络问题：配置acks为-1
2. mq内部服务崩溃- 消息未来得及同步，跨网络：如果acks为-1，那么会有多个follower确保接收到了消息，会选举产生新的leader partition，确保整个集群消息不会丢失
3. 消费者pull的时候，丢失消息 - 跨网络：消费端有消息重试机制，每次消费者处理完一批消息后，都需要给broker返回个response，里面包括已经消费的offset。
   broker收到offset后才会更新本地offset，否则下次消费者会重复读取该位置
   
   不要使用异步处理业务，应在业务结果落盘后再向broker更新offset


如何防止重复消费：幂等  https://blog.csdn.net/dl962454/article/details/128087396
1. 生产者开启自带的幂等，producer端，在send方法异常的时候会出现，更改系统配置prop.put（）改为true
2. 消费端
     * 消费端挂掉
       解决方案：在消息体中添加唯一标识，setnx存到redis中，做幂等
     * consumer 消费时间过长，Kafka消费端的参数max.poll.interval.ms定义了两次poll的最大间隔，它的默认值是 5 分钟，表示 Consumer 如果在 5 分钟之内无法消费完 poll方法返回的消息，那么Consumer 会主动发起“离开组”的请求。
       在离开消费组后，开始Rebalance，因此提交Offset失败。之后重新Rebalance，消费者再次分配Partition后，再次poll拉取消息依然从之前消费过的消息处开始消费，这样就造成重复消费。而且若不解决消费单次消费时间过长的问题，这部分消息可能会一直重复消费。
       整体上来说，如果我们在消费中将消息数据处理入库，但是在执行Offset提交时，Kafka宕机或者网络原因等无法提交Offset，当我们重启服务或者Rebalance过程触发，Consumer将再次消费此消息数据。
       解决方法：调整两个参数，max.poll.interval.ms 调大； max.poll.records 调小

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

问题：
1. 初始化的时候是A对象，而容器中以及注入到B中的都说代理对象，这样会有问题吗？
不会，代理类内部会持有一个目标类的引用，当调用代理对象方法时，实际上会去调用目标对象方法
2. 为社么要用三级缓存，而不是二级缓存？
二级也能解决循环依赖的问题；
spring中bean设计是：在需要时实例化，singletonFactory.getObject()是获取Bean，就是延迟实例化。
bean的声明周期是在bean创建完成后，通过后置处理器来完成创建aop代理对象。
==========
如果去掉二级缓存，则需要直接在一级缓存中直接创建bean的代理，这样违背了bean的生命周期

