/*

One of the exciting characteristics of Groovy is Meta Object Protocol (MOP). In a nutshell,
the term metaprogramming refers to writing code that can dynamically change its behavior at
runtime. A Meta Object Protocol refers to the capabilities of a dynamic language that enable
metaprogramming. In this recipe, we are going to look at one of the capabilities of the MOP,
which is ExpandoMetaClass.
Groovy's ExpandoMetaClass lets you assign behavior and state to classes at runtime
without editing the original source code; it is essentially a layer above the original class.
In the next section of the recipe, we will show you how to achieve such a result.


Adding a new method to a Groovy (or Java) class is straightforward. We are going to perform
the following given steps to add a getInEuros method to the BigDecimal Java class in
order to convert US dollars to euros:

*/


import java.text.NumberFormat

BigDecimal.metaClass.getInEuros = { ->
  def exchangeRate = 0.763461
  def nf = NumberFormat.getCurrencyInstance(Locale.US)
  nf.setCurrency(Currency.getInstance('EUR'))
  nf.format(delegate * exchangeRate)
}

assert 1500.00.inEuros == 'EUR1,145.19'
