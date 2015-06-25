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

  def text() = Property[String](btn.text = _)

  def map(fn: String => String) =
    btn.text = fn(btn.text)

  val value = btn.text
}
