/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to ToggleButton
 */
abstract class ToggleButton_ extends Widget_ {

  private val btn = new ToggleButton

  def pack() = btn

  val text = Property[String](btn.text = _)

  val toggled = Property[Boolean](b => if(b) btn.doClick())
}
