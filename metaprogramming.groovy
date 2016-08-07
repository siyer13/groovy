package org.groovy.cookbook

class Book {
  String title
  Author Author
  Long year
  Long pages

  Long getAmazonSalesPosition() {
    new Random().nextInt(1) + 1
  }

  void attachReview(String review) { }
}

class Author {
  String name
  String lastName
}

//assert 'java.lang.String' == String.name
//assert 'org.groovy.cookbook.Author' == Author.name


Author a = new Author(name: 'Ernest', lastName: 'Hemingway')
Book book = new Book()

book.with {
  title = 'The old man and the sea'
  year = 1952
  pages = 200
  author = a
}

book.properties.each { println it}
