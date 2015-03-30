/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import javax.swing.JOptionPane

/**
 * @author Steven Dobay
 */
object popups {

  trait Result

  case object YES extends Result

  case object NO extends Result

  case object CANCEL extends Result

  /**
   * Creates a warning message
   *
   * @param parent
   * @param msg
   * @param title
   */
  def warning(parent: Frame, msg: String, title: String = ""): Unit =
    JOptionPane.showMessageDialog(
      parent.getWrapped,
      msg, title,
      JOptionPane.WARNING_MESSAGE
    )

  /**
   * Creates an information message
   *
   * @param parent
   * @param msg
   * @param title
   */
  def info(parent: Frame, msg: String, title: String = ""): Unit =
    JOptionPane.showMessageDialog(
      parent.getWrapped,
      msg, title,
      JOptionPane.INFORMATION_MESSAGE
    )

  /**
   * Creates a plain message
   *
   * @param parent
   * @param msg
   * @param title
   */
  def plain(parent: Frame, msg: String, title: String = ""): Unit =
    JOptionPane.showMessageDialog(
      parent.getWrapped,
      msg, title,
      JOptionPane.PLAIN_MESSAGE
    )

  /**
   * @param parent
   * @param msg
   * @param title
   * @return with a result from the dialog
   */
  def yesNo(parent: Frame, msg: String, title: String = "") = {
    val res = JOptionPane.showConfirmDialog(
      parent.getWrapped,
      msg, title,
      JOptionPane.YES_NO_OPTION
    )
    if (res == 0) YES else NO
  }

  /**
   * @param parent
   * @param msg
   * @param title
   * @return with a result from the dialog
   */
  def yesNoCancel(parent: Frame, msg: String, title: String = "") = {
    val res = JOptionPane.showConfirmDialog(
      parent.getWrapped,
      msg, title,
      JOptionPane.YES_NO_CANCEL_OPTION
    )
    if (res == 0) YES else if (res == 1) NO else CANCEL
  }

  /**
   * @param parent
   * @param msg
   * @param title
   * @return with a String given by the user
   */
  def input(parent: Frame, msg: String, title: String = ""): String =
    JOptionPane.showInputDialog(
      parent.getWrapped,
      msg, title
    )

  /**
   * @param parent
   * @param msg
   * @param title
   * @param choices
   * @param defaultChoice
   * @return with the chosen value as String
   */
  def choice(parent: Frame, msg: String, title: String = "",
             choices: Array[AnyRef], defaultChoice: Any) =
    JOptionPane.showInputDialog(
     parent.getWrapped,
     msg, title,
     JOptionPane.QUESTION_MESSAGE, null,
     choices, defaultChoice
    )

}