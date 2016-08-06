@Grab('mysql:mysql-connector-java:5.1.25')
@GrabConfig(systemClassLoader = true)
import groovy.sql.Sql
def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/WEATHERINFO', 'root', 'password','com.mysql.jdbc.Driver')
println sql.connection.catalog
