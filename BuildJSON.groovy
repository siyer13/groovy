class Person {
  String first
  String last
  def sigs
  def tools
}

john = new Person(first: "John", last: "Smith",
sigs: ['Java', 'Groovy'], tools: ['script': 'Groovy', 'test': 'Spock'])

bldr = new groovy.json.JsonBuilder(john)
writer = new StringWriter()
bldr.writeTo(writer)

println writer
