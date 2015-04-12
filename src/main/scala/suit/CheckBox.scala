/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import javax.swing.JCheckBox

/**
 * @author Steven Dobay
 */
case class CheckBox(private val initIsChecked: Boolean = false)
 extends Component with Bindable[Boolean] with Stateful[Boolean] {

  private val btn = new JCheckBox()
  btn.setSelected(initIsChecked)

  def isSelected() = btn.isSelected

  def select() = btn.setSelected(true)

  def deselect() = btn.setSelected(false)

  def text = btn.getText

  def text_=(t: String) = btn.setText(t)

  protected def onChangeDoBind(v: HolderOf[Boolean]) =
   changeEvents += (_ => v.value = isSelected())

  protected type EventType = ChangeEvent
  protected type ListenerType = ActionListener

  protected def createAndGetListener(proc: EventType => Unit) = {
   val listener = new ListenerType {
    override def actionPerformed(e: java.awt.event.ActionEvent): Unit =
     proc(ChangeEvent(e))
   }
   btn.addActionListener(listener)
   listener
  }

 protected def removeListener(l: ListenerType) =
  btn.removeActionListener(l)

  protected[suit] def wrapped = btn

  def className = "CheckBox"
}
