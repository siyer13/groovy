def PickEven(n,block) {
  for( i = 0 ; i < n; i += 2) {
    block(i)
  }
}

PickEven(10, { println it } )

PickEven(10) {
  evenNumber -> println evenNumber
}
