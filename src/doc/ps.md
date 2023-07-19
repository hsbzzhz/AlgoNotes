	![img_1.png](img_1.png)
1. 同源策略怎么解决跨域问题：
	保证同源：协议，域名，端口都相同
	目的是：保护用户信息安全，为了防止跨脚本攻击，禁止浏览器通过脚步访问非同源数据
    受到影响的资源: cookie, dom, ajax请求响应结果会被拦截
	
	CORS方案：
    Html5 新特性， 只需要在服务端在响应头中Access-Control-Allow-Origin: *即可
    JSON with padding