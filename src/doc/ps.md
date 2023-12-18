### http过程
1. 客户端解析url，建立tcp连接
2. 客户端发送http request（包括请求行，请求头，请求体）
3. 服务器接收并解析请求消息，执行业务操作，生成响应消息
4. 服务器发送HTTP respond（包括响应行，响应头和响应体）
5. 客户端接收并解析 respond，浏览器对内容进行渲染
6. 客户端和服务器关闭TCP连接

### [同源策略怎么解决跨域问题](https://blog.csdn.net/m0_63070387/article/details/128692886)：
    保证同源：协议，域名，端口都相同 
    目的是：保护用户信息安全，为了防止跨脚本攻击，禁止**浏览器**通过脚步访问非同源数据 
    会带来跨域问题: 阻止一个域的javascript和另一个域的内容进行互通--- cookie, dom, ajax**请求响应结果会被拦截**
    解决方法：
1. CORS 后端设置<br>
   ![img.png](src/cors.png)
   - 当浏览器在进行跨域请求时，会在请求头中添加`origin`表明发送者的源（前端地址）
   - 当服务器接收到请求，并查看origin头部时，如果设置过允许访问，就**添加到Access-Control-Allow-Origin到响应头中**
   - 浏览器通过返回头部，判断是否可以进行跨域请求
   - 在Springboot中使用cors
     - 在controller上添加@CrossOrigin(origins="http://localhost:8080")注解，指定允许哪些orgins跨域
     - 实现 WebMvcConfigurer 接口，重写adsCorsMappings方法，设置允许跨域
     - 配置CorsFilter：配置类，类中明确支持域信息等配置
2. JSONP
   - `<script>`标签允许不同源请求脚本
   - ?后方法提供接口参数，所以只支持GET，不支持POST
   ```javascript
    <script>
        function param(data){
            console.log(data);
        }
    </script>
    <script 
         
        src="http://localhost:8000/server/index.php?callback=param" /* 将param函数作为参数传递给服务器 */
        type="text/javascript">
    </script>
    ```
3. 代理<br>
 ![img.png](src/proxy.png)
   - 配置一个和前端同源的代理服务器，然后通过代理服务器去请求后端数据，然后返回
   - 相当于浏览器只跟前端服务器和前端代理服务器打交道
   - 在vue3中配置 vue.config.js
   ```javascript
    module.exports = {
    devServer:{
    //设置代理
    proxy: { //代理是从指定的target后面开始匹配的，不是任意位置；配置pathRewrite可以做替换
           '/api': { //axios访问 /api ==  target + /api
           target: 'http://localhost:8080', // 要访问的接口域名
           changeOrigin: true, //创建虚拟服务器
           pathRewrite: {
                '^/api': ''    //重写接口，去掉/api， 在代理过程中是否替换掉/api/路径
                }
           }
         }
       }
    }
   ```

### [JS中的闭包函](https://blog.csdn.net/m0_58646138/article/details/124846951):
```javascript
function a() {
    var i = 0;
    function b() {
        alert(++i)
    }
    return b;
}
var c = a(); // 实际上c指向了函数b
c(); // 变量c 引用了 函数b，形成了一个b包。（因为b被a引用，b又同时被c引用）

c = null; // 手动释放闭包对象

```
结果就是: **a() 执行后，闭包使得js的垃圾回收机制不会回收a占用内存，因为b依赖a中的变量i**
- vue中使用场景（函数无法访问其他函数的变量，需要闭包来达成）
  * 小范围代替全局变量
  * 返回值：给对象设置**私有变量**，并用内部b方法返回值去访问（访问私有变量的特权方法）
  * 原生JS中的setTimeout（作用是能够延迟一段时间后执行指定的函数）
    * 传递的第一个函数不能带参数，通过闭包可以实现传参效果
  * 连续触发事件n次，但在一定时间内只执行1次：=>实现**节流**和**防抖**
    * 防抖应用于**动态搜索框**，防止多次查询
    ```javascript
    export function throttle(func, wait) {
    // 传2个参数，一个是回调函数，一个是间隔时间
       let timerId;
       return function (...args) {
          if (!timerId) {
              timerId = setTimeout(() => {
                  timerId = null;
                  func.apply(this, args);
              }, wait);
          }
       }
    }
    ```
  * 创建特权方法用于访问控制
- 缺点
  * 增大内存消耗，引用的私有便携不会主动被销毁，需要手动赋null
  * 闭包设计跨域访问，会造成性能损失：可以通过把跨作用域变量 存在局部变量中，然后直接访问局部变量


### [mvvm和mvc框架](https://segmentfault.com/a/1190000015895017)
- View, Model, View-Model
  - View 通过View-Model的DOM Listeners 将事件绑定到Model上
  - Model 通过Data Bindings来管理View中的数据
  - ViewModel为中间桥接

* vue如何实现mvvm：
  - 响应式 Object.defineProperty，监听data属性变化
  - 模板：vue是通过render函数，把template解析为虚拟DOM
  - 渲染：将虚拟dom渲染为html
  - 最后通过v-model 指定在表达`<input>`、`<texarea>`以及`<select>`元素上创建**双向数据绑定**

**vm做的事情就是把dom操作封装起来，开发人员不用管Model 和 view之间时如何互相影响的，只要model发生改变了，view上肯定会呈现出现，当用户修改了view，model上也会跟着修改。这样开发人员可以不管dom，只需要注意如何操作model**
- MVC模型，Controller 是 view 和 model层间的操作


## vue
### [js的常见问题](https://blog.csdn.net/Jueyue15/article/details/120155179)

### vue中css动态绑定
- 通过class对象或数组
  ```html
  <div v-bind:class="{ active: isActive, 'text-danger': hasError }"></div>
  ```
- 通过style语法
   ```html
   <div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"></div>
   ```
### computed 和 watch 区别
- computed：是计算属性，依赖其它属性值，并且 computed 的值有缓存，只有它依赖的属性值发生改变，下一次获取 computed 的值时才会重新计算 computed 的值；
- watch：更多的是「观察」的作用，类似于某些数据的监听回调 ，每当监听的数据变化时都会执行回调进行后续操作；
### vue2到vue3更新
- 选项式 -> 组合式
  - 选项式：代码分不同属性data、computed、methods等
  - 组合式：均在setup()中，根据业务逻辑区分
  - 生命周期函数有调整
- 数据双向绑定原理不同
  - 【vue2.x】Object.defineProperty() 对数据进行劫持对象属性的getter，setter，结合发布者订阅者方式实现
    - 只能监听对象某个属性
    - 如果需要监听多属性多，需要用到for循环，消耗资源多
  - 【vue3】ES6的Proxy对数据代理
    - 劫持整个对象
### vue中常见组件
独立ui模块，比较常用的elementUI，route，axios
- 组件的核心概念：
    - 属性
    - 事件
    - 插槽
#### axios 与 promise
- promise 是es6 提供的异步解决方案
- axios是由promise封装的http库
- ajax异步请求数据时，不知道数据具体回来的事件，需要传入callback方法当ajax异步请求完成后执行
- promise对象接收resolve和reject两个参数，分别动作完成后的解析和捕获异常处理
  - new Promise().then()中获取成功数据
  - new Promise().catch()获取失败信息
#### [route](https://blog.csdn.net/Litt_White/article/details/126435871)
- 配置参数
  - `path` 用户配置访问路径
    - 带`/`表示绝对路径，不带`/`表示相对父路由下的路径
  - `name` 用户路由命名
  - `component` 表示需要映射的组件
  - `redirect` 表示重定向
  - `children` 用于路由嵌套
- 路由传参
  - 通过 `query` 传参和通过 `param` 传参
  - `keep-alive` 标签包裹`router-view`，对应所有路径的组件都会被缓存，避免组件频繁的被创建和销毁
#### element-ui
- vue的UI组件库

### vue中组件通信
#### 父组件传值给子组件（单向数据流）
- 步骤一：将父组件的数据传递进来，并在子组件用props接收
- 步骤二：将传递进来的数据通过初始值保存起来
#### 自定义事件（子组件给父组件传递数据）
- 步骤一：在父组件中定义事件$on() 来监听子组件
- 步骤二：在子组件中使用$emit 触发事件，第一个参数是事件名，后边的参数是要传递的数据
- 步骤三：在自定义事件中用一个参数来接受
#### 非父子间通信（eventbus）- 不支持响应式
- 步骤一：创建一个空的vue实例作为中央事件总线
- 使用$on监听
- 使用bus.$emit 来发送消息
#### *其他
- 使用vuex实现多个组件共享信息
- 通过dom操作来实现，$parent $children / ref

### 更新singleSelect自定义组件
- 更新公共组件singleSelect
  - :是v-bind的缩写，是动态绑定
  - 给`el-select`标签上，绑定`disable`属性，然后传入`isOptionDisable`
  - 调用`executeMethod('select-disable-by-condition')`的公共方法（mixin方法，在组件中引入，mixin中的方法和属性也就都并入到该组件中），为算子文件中配的key
  - 在js中创建函数名为value，实现业务返回bool，即给isOptionDisable传入值
- [el-form form表单校验](https://blog.csdn.net/m0_65132206/article/details/124853341)
  1. 添加属性:rule = ”myrules“
  2. myrules中定义校验规则，message 和 trigger: blur 在表单失焦时触发
  3. 在rules中自定义validator函数，校验通过直接返回callback()函数空，否在callback(new Error('核验不通过'))
 
  [https://github.com/influxdata/chronograf/issues/5607](https://blog.csdn.net/qq_44406232/article/details/131223041)https://blog.csdn.net/qq_44406232/article/details/131223041
  https://zhuanlan.zhihu.com/p/459827627

1. could you pls talk about the daily job for this role?
2. what's the typical career path for someone in this role at your company?
3. What's professional development opportunities ara available for this position?
4. what's the structure of the team/department?
