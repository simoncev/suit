/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JCheckBox
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class CheckBox(private val initIsChecked: Boolean = false)
 extends Component with Bindable[Boolean] {

  private val btn = new JCheckBox()
  btn.setSelected(initIsChecked)

  def isSelected() = btn.isSelected

  def select() = btn.setSelected(true)

  def deselect() = btn.setSelected(false)

  def text = btn.getText

  def text_=(t: String) = btn.setText(t)

  protected def onChange(v: HolderOf[Boolean]) = {
    btn.addChangeListener(new ChangeListener {
      override def stateChanged(e: ChangeEvent): Unit =
       v.value = isSelected()
    })
  }

  protected[suit] def wrapped = btn

  def className = "CheckBox"
}
