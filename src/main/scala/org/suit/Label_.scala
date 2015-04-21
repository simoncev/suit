/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
abstract class Label_(private val t: String) extends Widget_ {
  private var lab = new Label(t)

  def pack() = lab

  val text = Property[String](lab.text = _)
}
