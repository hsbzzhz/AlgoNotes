# [常见命令](https://developer.aliyun.com/article/842453)

## impoartant!
1. `top`：查看内存/显示系统当前进程信息
2. `df -h`：查看磁盘储存状况
3. iotop：查看IO读写（yum install iotop安装）
   - `iotop -o`：直接查看比较高的磁盘读写程序
4. `netstat -tunlp | grep 8081`：查看使用8081端口号（用于显示 tcp，udp 的端口和进程等相关情况）
5. `lsof -i:8080`：查看端口号8080占用情况
6. `ps aux|grep java`：查看java所有进程
## 文件系统
1. 创建空文件：touch
2. 创建目录：mkdir
3. 查看文件内容：`cat desc.txt`
4. 分页查看文件内容：`more desc.txt`
5. 查看文件尾内容：`tail -100 desc.txt`：查看desc.txt的最后100行内容
   - `tail -f /home/studio/logs/runlog/info.log` 循环读取日志
6. 拷贝：
    - `cp desc.txt /mnt/`：拷贝desc.txt到/mnt目录下
    - `cp -r test /mnt/`：拷贝test目录到/mnt目录下（-r循环目录拷贝）
7. 剪切（改名）：
    - `mv 原名 新名`
    - `mv desc.txt /mnt/`：剪切文件desc.txt到目录/mnt下
8. 删除：`rm -rf test`：删除test目录，-r递归删除，-f强制删除
9. 搜索文件：`find /opt -name ‘*.txt’`：在opt目录下查找以.txt结尾的文件
10. 显示或配置网络设备：`ifconfig`
11. 显示网络相关信息：netstat：
    - `netstat -a`：列出所有端口
    - `netstat -antp | grep 8081'`：查看使用8081端口号的连接和进程信息
12. 压缩和解压   -z表示用gzip压缩
    - `tar -zcvf test.tar.gz ./test`：打包当前目录下test目录 为test.tar.gz文件
    - `tar -zcvf test.tar.gz *.txt`：打包当前目录下以txt结尾文件 为test.tar.gz文件
    - `tar -zxvf test.tar.gz`：解压test.tar.gz文件
13. 改变文件或目录的拥有者和组：chown
    - `chown paas:nginx desc.txt`：变更文件desc.txt的拥有者为paas，用户组为nginx
    - `chown -R paas:paas test`：变更test及目录下所有文件的拥有者为paas，用户组为paas
14. 改变文件或目录的访问权限：chmod
    - `chmod u+x test.sh`：权限范围：u(拥有者)g(群组)o(其它用户)， 权限代号：r(读权限/4)w(写权限/2)x(执行权限/1)#给文件拥有者增加test.sh的执行权限
15. vim 
    - :q退出 :q!强制退出 :wq!保存退出
    - `Ctrl + e` 光标到结尾
## docker k8s 相关
1. 从服务器上，拷贝jar包到指定容器目录内部，前面是源文件jar包，后面是容器id:/home/studio/web 目录
    - `docker cp /home/paas/datastudiobackend-core-2.20.90-SNAPSHOT.jar a0ce6d20e465:/home/studio/web`
2. 进入容器内部
    - `docker exec -u root -it a0ce6d20e465 bash`：以root用户进入
    - `docker exec -it a0ce6d20e465 bash`：以普通用户进入，如果碰到需要重启容器脚本，必须要以普通用户进入
3. docker run
    - `docker run -p 4000:80 helloworld`：创建启动镜像helloworld的容器，容器内的80端口映射在宿主机的4000端口上（每次运行都会创建新的容器）
    - `docker start|stop|restart 5fe8650b7245`：启动|停止|重启 容器
