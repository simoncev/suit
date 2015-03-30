/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import java.awt.event
import java.awt.event.ActionListener
import javax.swing.JMenuItem

/**
 * @author Steven Dobay
 */
case class TextMenuItem(private val initTitle: String)
  extends MenuItem(new JMenuItem(initTitle)) {

  private val textMenuItem = menuItem.asInstanceOf[JMenuItem]

  /**
   * @return with the title of the menuitem
   */
  def title = textMenuItem.getText

  /**
   * Sets the menuitem's title
   * @param t
   */
  def title_=(t: String) = textMenuItem.setText(t)

  /**
   * @return with the name of the class
   */
  def className = "TextMenuItem"

  /**
   * @param proc
   */
  override def onAction(proc: ActionEvent => Unit): Unit =
    textMenuItem.addActionListener(new ActionListener {
      override def actionPerformed(e: event.ActionEvent): Unit =
        proc(ActionEvent(e))
    })

  override def equals(obj: Any) =
    if(obj.isInstanceOf[Button])
      obj.asInstanceOf[Button].getWrapped == getWrapped
    else if(obj.isInstanceOf[JMenuItem])
      obj.asInstanceOf[JMenuItem] == getWrapped
    else false
}
