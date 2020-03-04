# ncov
spring boot 项目，用于查询疫情数据<br>
=
目前整合了mongoDB,mysql,mybatis,后续视项目需要添加<br>
初步打算做成疫情地图，疫情小区数据，详细数据查询，数据分析,定时从mongoDB中获取当天数据存至mysql功能。<br>
此项目目前为后端项目，前端是分离还是在此项目中添加，视自己时间决定<br>
<br>
疫情数据的爬取可参考源码项目Isaac Lin大神分享https://github.com/BlankerL/DXY-2019-nCoV-Crawler.git<br>
或可打包成docker容器https://github.com/wentaojava/2019-nCoV.git
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
TODO:<br>
* 数据分析
--
<br>
<br>
<br>
DONE:<br>
* 定时从mongoDB中获取当天数据存至mysql功能
* 疫情地图数据获取
* 各省份对应城市详细数据查询

<br>
maven打包镜像之前需要执行maven install，先把项目打成jar包
<br>
 docker run -itd --name ncov-0.0.1-SNAPSHOT -p 8001:8001 --restart always ncov-spring-boot:ncov
