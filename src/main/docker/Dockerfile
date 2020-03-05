FROM java:8
#需要暴露出去的端口，也就是我项目的端口
EXPOSE 8001
#作者信息
MAINTAINER wentao
#将主机环境的jar包，以文件名 ncov-java.jar添加到docker镜像中。就是因为这条命令，mavnen运行报错
ADD ncov-1.0.1.jar ncov-java.jar
#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
#启动容器之后，默认的运行命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom  --spring.profiles.active=product","-jar","/ncov-java.jar"]
