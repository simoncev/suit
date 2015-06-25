/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
abstract class ProgressBar_(private val lowerBound: Int = 0,
                            private val upperBound: Int = 100,
                            private val defaultValue: Int = 0)
  extends Widget_ {

  private val bar
    = new ProgressBar(lowerBound, upperBound, defaultValue)

  val min = Property[Int](bar.min = _)

  val max = Property[Int](bar.max = _)

  def value() = Property[Int](bar.value = _)

  def map(fn: Int => Int) = bar.value = fn(bar.value)

  def pack() = bar
}
