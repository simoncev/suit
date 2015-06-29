/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
abstract class Label_(private val t: String) extends Widget_ {
  private val lab = new Label(t)

  def pack() = lab

  val text = Property[String](lab.text = _)

  def value() = lab.text

  def map(fn: String => String) =
    lab.text = fn(lab.text)
}
