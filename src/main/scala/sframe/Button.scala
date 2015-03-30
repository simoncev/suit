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
 private var dim = Dim(0, 0)
 private var container: Option[Container] = None

 /**
  * @return with the size of th button
  */
 def size = dim

 /**
  * Sets the size of the button
  * @param d
  */
 def size_=(d: Dim) = {
  dim = d
  button.setSize(dim)
 }

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
 def getWrapped = button

 /**
  * @return with a pointer to the parent optionally
  */
 override def getContainer: Option[Container] = container

 /**
  * Sets the parent of the component
  * @param c
  */
 def setContainer(c: Container) = container = Some(c)

 /**
  * @return with the name of the class
  */
 def className = "Button"

 /**
  * @param key
  * @return with the property at the given key
  */
 def property(key: String) = button.getClientProperty(key)

 /**
  * Puts the property to the client property
  */
 def property_=(key: String, value: AnyRef) =
  button.putClientProperty(key, value)

 override def equals(obj: Any) =
  if(obj.isInstanceOf[Button])
   obj.asInstanceOf[Button].getWrapped == getWrapped
  else if(obj.isInstanceOf[JButton])
   obj.asInstanceOf[JButton] == getWrapped
  else false
}
