def url = new URL('http://groovy.codehaus.org/')
def connection = url.openConnection()
connection.requestMethod = 'GET'
if (connection.responseCode == 200) {
  println connection.contentType
  println connection.lastModified
  connection.headerFields.each { println "> ${it}"}
} else {
     println 'An error occured: ' +
                connection.responseCode + ' ' +
                connection.responseMessage
}
