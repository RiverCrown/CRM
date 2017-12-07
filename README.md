# CRM系统

系统采用**本地mysql数据库**，因此项目中的数据库配置文件需要和本地数据库配置相同（如用户名密码），所以在pull或者clone以后需要手动配置一下。

配置文件的路径为：

crm/src/main/resources/application.properties

文件中的配置为：

```
spring.datasource.url = jdbc:mysql://localhost:3306/crm
spring.datasource.username = root
spring.datasource.password = ******
spring.datasource.driver-class-name= com.mysql.jdbc.Driver
spring.jpa.database = mysql
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
```

一般来说只要按需修改

```
spring.datasource.username = root
spring.datasource.password = ******
```

这两项就可以了。