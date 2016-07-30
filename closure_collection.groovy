def list = [ 1,2,3,4,5,6,7]

list.find {
  value -> println value == 2
}

def hashmap = [a:1, b:2, c:3]

hashmap.each {
  k,v -> print "${k} : ${v}"
  println ""
}
