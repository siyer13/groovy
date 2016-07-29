// list test
list = [1,2,3]
println list.size

// for test
for( i in 1..10)
print i

println ""

//upto on java.lang.Integer
1.upto(10) {
  print "$it"
}

println ""

// another way
10.times {
  print "$it"
}

println ""

// step helps us skip values
// to print only even numbers till 10
0.step(10,2) {
  print "$it"
}

println ""

// ?. to avoid null pointer exception
def foo(str) {
  str?.reverse()
}

println foo('evil')
println foo(null)

println ""

// doesn't enforce to handle exceptions
def openFile(filename) {
  new FileInputStream(filename)
}

openFile('sridhar.txt')
