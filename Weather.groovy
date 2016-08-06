@Grab('mysql:mysql-connector-java:5.1.25')
@GrabConfig(systemClassLoader = true)
import groovy.sql.Sql
def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/WEATHERINFO', 'root', '','com.mysql.jdbc.Driver')
println sql.connection.catalog
println "City Temperature"
sql.eachRow('SELECT * from WEATHER') {
printf "%-20s%s\n", it.CITY, it[1]
}

bldr = new groovy.xml.MarkupBuilder()

bldr.WEATHER {
  sql.eachRow('SELECT * FROM WEATHER') {
    CITY(name: it.CITY, temperature: it.TEMEPERATURE)
  }
}


dataSet = sql.dataSet('WEATHER')
citiesBelowFreezing = dataSet.findAll { it.TEMEPERATURE < 32}
println "Cities below freezing:"
citiesBelowFreezing.each {
  println it.CITY
}
