/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import java.awt.event.ActionListener
import javax.swing.JButton

/**
 * @author Steven Dobay
 */
case class Button(private val initText: String = "") extends Component {
 private val button = new JButton(initText)

 /**
  * @return with the button's text
  */
 def text = button.getText

 /**
  * Sets the button's text
  * @param t
  */
 def text_=(t: String) = button.setText(t)

 /**
  * Adds action handling to the button
  * @param proc
  */
 override def onAction(proc: ActionEvent => Unit): Unit =
   button.addActionListener(new ActionListener {
    override def actionPerformed(e: java.awt.event.ActionEvent): Unit =
     proc(ActionEvent(e))
   })

  /**
   * @return with a pointer to the wrapped JComponent
   */
 def wrapped = button

 /**
  * @return with the name of the class
  */
 def className = "Button"

 /**
  * @param obj
  * @return true if the wrapped objects equals
  */
 override def equals(obj: Any) =
  if(obj.isInstanceOf[Button])
   obj.asInstanceOf[Component].wrapped == wrapped
  else if(obj.isInstanceOf[JButton])
   obj.asInstanceOf[JButton] == wrapped
  else false
}
