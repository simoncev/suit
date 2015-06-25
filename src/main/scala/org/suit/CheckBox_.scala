/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
case class CheckBox_(private val isChecked: Boolean = false)
 extends Widget_ {

  private val btn = new CheckBox(isChecked)

  val selected = Property[Boolean](
    b => if(b) btn.select() else btn.deselect()
  )

  val text = Property[String](btn.text = _)

  def value() = btn.isSelected()

  val holder = Property[HolderOf[Boolean]](btn.holder = _)

  val onChange = Property[ChangeEvent => Unit](btn.changeEvents += _)

  def pack() = btn
}
