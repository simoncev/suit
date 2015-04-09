/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to Spinner
 */
abstract class Spinner_ extends Widget_ {

  private val spinner = new Spinner

  def pack() = spinner

  val value = Property[Int](spinner.value = _)
}
