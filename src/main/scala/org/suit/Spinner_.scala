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

  val value = Property[Int](spinner.value = _)

  val onChange = Property[ChangeEvent => Unit](pack().changeEvents += _)
}
