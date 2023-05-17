# [常见命令](https://developer.aliyun.com/article/842453)

## impoartant!
1. top：查看内存/显示系统当前进程信息
2. df -h：查看磁盘储存状况
3. iotop：查看IO读写（yum install iotop安装）
4. iotop -o：直接查看比较高的磁盘读写程序
5. netstat -tunlp | grep 端口号：查看端口号占用情况（1）
6. lsof -i:端口号：查看端口号占用情况（2）
7. uptime：查看报告系统运行时长及平均负载
8. ps aux：查看进程
## 文件系统
1. 创建空文件：touch
2. 创建目录：mkdir
3. 查看文件内容：`cat desc.txt`
4. 分页查看文件内容：`more desc.txt`
5. 查看文件尾内容：`tail -100 desc.txt`：查看desc.txt的最后100行内容
6. 拷贝：
   - `cp desc.txt /mnt/`：拷贝desc.txt到/mnt目录下
   - `cp -r test /mnt/`：拷贝test目录到/mnt目录下（-r循环目录拷贝）
## docker 相关