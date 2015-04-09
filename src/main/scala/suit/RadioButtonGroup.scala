/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JPanel
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class RadioButtonGroup()
  extends Container with Component with Bindable[List[Boolean]] {
  private val panel = new JPanel
  private var buttons = new Array[RadioButton](5)

  def +=(btn: RadioButton) = {
    panel.add(btn.wrapped)
    buttons = Array.concat(buttons, Array(btn))
  }

  def withButton(btn: RadioButton) = {
    panel.add(btn.wrapped)
    buttons = Array.concat(buttons, Array(btn))
  }

  def ++=(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
  }

  def withButtons(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
    this
  }

  def -=(btn: RadioButton) = {
    panel.remove(btn.wrapped)
    buttons = buttons.filter(_ != btn)
  }

  def getButtons = buttons

  def selected: Option[String] = buttons.find(_.isSelected) match {
    case Some(btn) => Some(btn.asInstanceOf[RadioButton].text)
    case _         => None
  }

  protected def onChange(v: HolderOf[List[Boolean]]) = {
     for(btn <- buttons) btn.wrapped.addChangeListener(
       new ChangeListener {
        override def stateChanged(e: ChangeEvent): Unit =
          v.value = buttons.map(_.isSelected).toList
     })
  }

  def className = "RadioButtonGroup"

  def wrapped = panel
}
