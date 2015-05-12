/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
abstract class Button_(private val txt: String = "") extends Widget_ {
  private var btn = new Button(txt)

  def pack() = btn

  val text = Property[String](btn.text = _)
}
