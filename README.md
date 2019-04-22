# LoginRegisterDemo
A very simple project for login and register by Java Web and Android

version 1.0
1. DBUtils 中的文件 db-config.properties 修改对应的数据库信息
jdbc.url=jdbc:mysql://localhost:3306/数据库名称?useUnicode=true&characterEncoding=utf8
jdbc.username=数据库账号
jdbc.password=数据库密码
2. Android 中的文件 strings.xml 修改对应的服务器地址
<string name="s_url_login">http://服务器地址/LoginRegisterDemo/LoginServlet</string>
<string name="s_url_register">http://服务器地址/LoginRegisterDemo/RegisterServlet</string>
