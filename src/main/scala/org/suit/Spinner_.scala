/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 * For properties' meaning go to Spinner
 */
abstract class Spinner_ extends Widget_ {

  private val spinner = new Spinner

  def pack() = spinner

  def value() = Property[Int](spinner.value = _)

  def map(fn: Int => Int) = spinner.value = fn(spinner.value)

  val onChange = Property[ChangeEvent => Unit](pack().changeEvents += _)
}
