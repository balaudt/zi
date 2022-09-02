package zi.common

import org.scalatest.Assertions
import org.testng.Assert._
import org.testng.annotations.{DataProvider, Test}

class MathTest extends Assertions {
  @DataProvider
  def binpowData() = {
    Array(Array[Object]((5L, 7L, 78125L)), Array[Object]((14L, 9L, 20661046784L)))
  }

  @Test(dataProvider = "binpowData")
  def testBinpow(test: (Long, Long, Long)) {
    val (a, b, expected) = test
    assertEquals(Math.binpow(a, b), expected)
  }

  @DataProvider
  def binpowModData() = {
    Array(Array[Object]((5L, 7L, 19L, 16L)), Array[Object]((14L, 9L, 121L, 92L)))
  }

  @Test(dataProvider = "binpowModData")
  def testBinpowMod(test: (Long, Long, Long, Long)) {
    val (a, b, m, expected) = test
    assertEquals(Math.binpowMod(a, b, m), expected)
  }

  @DataProvider
  def binFibData() = {
    Array(Array[Object](Long.box(17), (1597, 2584)), Array[Object](Long.box(33), (3524578, 5702887)))
  }

  @Test(dataProvider = "binFibData")
  def testBinFib(n: Long, expected: (Long, Long)): Unit = {
    assertEquals(Math.binFib(n), expected)
  }

  @DataProvider
  def gcdData() = {
    Array(Array[Object]((12L, 90L, 6L)), Array[Object]((21L, 56L, 7L)))
  }

  @Test(dataProvider = "gcdData")
  def testGcd(test: (Long, Long, Long)): Unit = {
    val (a, b, expected) = test
    assertEquals(Math.gcd(a, b), expected)
  }

  @DataProvider
  def extGcdData() = {
    Array(Array[Object]((12L, 90L), (6L, -7L, 1L)))
  }

  @Test(dataProvider = "extGcdData")
  def testExtGcd(in: (Long, Long), expected: (Long, Long, Long)): Unit = {
    assertEquals(Math.extGcd(in._1, in._2), expected)
  }

  @DataProvider
  def primeTestData() = {
    Array(Array[Object]((561, false)))
  }

  @Test(dataProvider = "primeTestData")
  def testPrimeTest(test: (Int, Boolean)): Unit = {
    val (n, expected) = test
    assertEquals(Math.isPrime(n), expected)
  }
}
