/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

import java.awt.{event, Color}
import java.awt.event.{MouseListener, ActionListener}
import javax.swing.{BorderFactory, JButton}

/**
 * @author Steven Dobayű
 * Simple button object to make simple actions.
 */
case class Button(private val initText: String = "") extends Widget {

 private val button = new JButton(initText)

 button.putClientProperty("suit-wrapper", this)

  /**
   * Builder to set the text of the button.
   */
 def withText(txt: String) = {
   button.setText(txt)
   this
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
 protected[suit] def wrapped = button

 /**
  * @return with the name of the class
  */
 def className = "Button"
}
