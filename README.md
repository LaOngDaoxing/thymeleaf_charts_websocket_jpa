# thymeleaf_charts_websocket_jpa
websocket+线程轮询：后台使用线程定时查询数据库变动数据，若有变动将用websocket实时推送消息到前台页面显示

# 一、svn地址
&emsp;https://
<br/>&emsp;账号
<br/>&emsp;密码
# 二、本地项目
## 1、使用软件
&emsp;IntelliJ IDEA 2019.3 x64
## 2、配置
### （1）环境
&emsp;jdk1.8
<br/>&emsp;tomcat85_8090
### （2）导入jar包
&emsp;项目 右键》Maven》Update Project...（Alt+F5）
### （3）配置文件application.properties
* spring.datasource.url=修改数据库信息，ip、数据库名；
* server.port=IDEA启动项目/浏览器访问项目地址中的端口号8090
### （4）配置文件application-dev.properties
* server.servlet.context-path=/启动项目名称
## 3、启动
&emsp;SpringBoot项目的主配置文件ChartApplication.java》点击按钮Debug
## 4、注意
&emsp;SpringBoot项目在启动后，首先会去静态资源路径（resources/static/）下查找 index.html 作为首页文件。
<br/>&emsp;如果在静态资源路径（resources/static/）下找不到 index.html，则会到（resources/templates/）目录下找 index.html（使用 Thymeleaf 模版）作为首页文件。
## 5、启动后浏览器默认访问地址
&emsp;http://localhost:8090/thymeleaf_charts_websocket_jpa/
## 6、演示图
<table>
    <tr>
        <td><img src="https://raw.githubusercontent.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/main/src/main/resources/static/images/indexPic01.jpg"/></td>
        <td><img src="https://github.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/blob/main/src/main/resources/static/images/indexPic.png"/></td>
    </tr>
    <tr>
        <td><img src="https://raw.githubusercontent.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/main/src/main/resources/static/images/indexPic.png"/></td>
        <td><img src="https://github.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/blob/main/src/main/resources/static/images/indexPic.png"/></td>
    </tr>
</table>
![首页](https://raw.githubusercontent.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/main/src/main/resources/static/images/indexPic.png)
![示例图](https://github.com/LaOngDaoxing/thymeleaf_charts_websocket_jpa/blob/main/src/main/resources/static/images/indexPic.png)
# 三、项目部署linux服务器
## 1、项目打包
## 2、linux服务器地址
&emsp;192.168.21.120
<br/>&emsp;root
<br/>&emsp;123456
## 3、部署位置：
&emsp;/opt/developsoft/servers/tomcat/tomcat85_8090
## 4、部署注意：
	（1）ps -ef | grep thymeleaf_charts_websocket_jpa.jar查出进程号，kill -9 进程号；
	（2）替换jar；
	（3）./startup.sh 启动
## 5、启动后浏览器默认访问地址
&emsp;http://192.168.21.120:8090/thymeleaf_charts_websocket_jpa
# 四、jenkins自动部署
## 1、浏览器地址
&emsp;http://
<br/>&emsp;账号
<br/>&emsp;密码
## 2、启动
&emsp; 
## 3、启动后浏览器默认访问地址
&emsp;
