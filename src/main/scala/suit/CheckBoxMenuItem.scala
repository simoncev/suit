/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event
import java.awt.event.ActionListener
import javax.swing.JCheckBoxMenuItem

/**
 * @author Steven Dobay
 */
case class CheckBoxMenuItem(private val initTitle: String,
                            private val isChecked: Boolean = false)
  extends MenuItem(new JCheckBoxMenuItem(initTitle, isChecked)) {

  private def checkMenuItem = menuItem.asInstanceOf[JCheckBoxMenuItem]

  /**
   * @return with the title of the menuitem
   */
  def title = checkMenuItem.getText

  /**
   * Sets the checkMenuitem's title
   * @param t
   */
  def title_=(t: String) = {
    checkMenuItem.setText(t)
    this
  }

  /**
   * @return true if the menuitem is checked
   */
  def checked = checkMenuItem.getState == true

  /**
   * Checks the menuitem
   */
  def check() = {
    checkMenuItem.setState(true)
    this
  }

  /**
   * Unchecks the menuitem
   */
  def uncheck() = {
    checkMenuItem.setState(false)
    this
  }

  /**
   * @return with the name of the class
   */
  def className = "CheckBoxMenuItem"

  /**
   * @param proc
   */
  override def onAction(proc: ActionEvent => Unit): Unit =
    checkMenuItem.addActionListener(new ActionListener {
      override def actionPerformed(e: event.ActionEvent): Unit =
        proc(ActionEvent(e))
    })
}