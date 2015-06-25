/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 * For properties' meaning go to ToggleButton
 */
abstract class ToggleButton_ extends Widget_ {

  private val btn = new ToggleButton

  def pack() = btn

  def value() = btn.text

  def map(fn: String => String) = btn.text = fn(btn.text)

  val text = Property[String](btn.text = _)

  val toggled = Property[Boolean](b => if(b) btn.doClick())

  val holder = Property[HolderOf[Boolean]](btn.holder = _)

  val onChange = Property[ChangeEvent => Unit](btn.changeEvents += _)
}
