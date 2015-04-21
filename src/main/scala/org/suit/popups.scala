/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

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
   * @param title
   * @param msg
   */
  def warning(parent: Container, title: String, msg: String): Unit =
    JOptionPane.showMessageDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.WARNING_MESSAGE
    )

  /**
   * @param title
   * @param msg
   * @param parent
   * @return
   */
  def warning(title: String, msg: String)
             (implicit parent: Container): Unit =
   warning(parent, title, msg)

  /**
   * Creates an error message
   *
   * @param parent
   * @param title
   * @param msg
   */
  def error(parent: Container, title: String, msg: String): Unit =
    JOptionPane.showMessageDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.ERROR_MESSAGE
    )


  /**
   * @param msg
   * @param title
   * @param parent
   * @return
   */
  def error(msg: String, title: String)(implicit parent: Container): Unit =
   error(parent, title, msg)

  /**
   * Creates an information message
   *
   * @param parent
   * @param title
   * @param msg
   */
  def info(parent: Container, title: String, msg: String): Unit =
    JOptionPane.showMessageDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.INFORMATION_MESSAGE
    )

  /**
   * @param title
   * @param msg
   * @param parent
   * @return
   */
  def info(title: String, msg: String)(implicit parent: Container): Unit =
    info(parent, title, msg)

  /**
   * Creates a plain message
   *
   * @param parent
   * @param title
   * @param msg
   */
  def plain(parent: Container, title: String, msg: String): Unit =
    JOptionPane.showMessageDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.PLAIN_MESSAGE
    )

  /**
   * @param title
   * @param msg
   * @param parent
   * @return
   */
  def plain(title: String, msg: String)(implicit parent: Container): Unit =
    plain(parent, title, msg)

  /**
   * @param parent
   * @param title
   * @param msg
   * @return with a result from the dialog
   */
  def yesNo(parent: Container, title: String, msg: String) = {
    val res = JOptionPane.showConfirmDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.YES_NO_OPTION
    )
    if (res == 0) YES else NO
  }

  /**
   * @param title
   * @param msg
   * @param parent
   * @return
   */
  def yesNo(title: String, msg: String)(implicit parent: Container): Result =
    yesNo(parent, title, msg)


  /**
   * @param parent
   * @param title
   * @param msg
   * @return with a result from the dialog
   */
  def yesNoCancel(parent: Container, title: String, msg: String) = {
    val res = JOptionPane.showConfirmDialog(
      parent.wrappedContainer,
      msg, title,
      JOptionPane.YES_NO_CANCEL_OPTION
    )
    if (res == 0) YES else if (res == 1) NO else CANCEL
  }

  /**
   * @param title
   * @param msg
   * @param parent
   * @return
   */
  def yesNoCancel(title: String, msg: String)(implicit parent: Container): Result =
    yesNoCancel(parent, title, msg)

  /**
   * @param parent
   * @param title
   * @param msg
   * @return with a String given by the user
   */
  def input(parent: Container, title: String, msg: String): String =
    JOptionPane.showInputDialog(
      parent.wrappedContainer,
      msg, title
    )

  /**
   * @param title
   * @param msg
   * @param parent
   * @return with the input String
   */
  def input(title: String, msg: String)(implicit parent: Container): String =
    input(parent, title, msg)


  /**
   * @param parent
   * @param title: String
   * @param msg
   * @param choices
   * @param defaultChoice
   * @return with the chosen value as String
   */
  def choice(parent: Container, title: String, msg: String,
             choices: Array[AnyRef], defaultChoice: Any): AnyRef =
    JOptionPane.showInputDialog(
     parent.wrappedContainer,
     msg, title,
     JOptionPane.QUESTION_MESSAGE, null,
     choices, defaultChoice
    )

  /**
   * @param title
   * @param msg
   * @param choices
   * @param defaultChoice
   * @param parent
   * @return with a Result
   */
  def choice(msg: String, title: String,
             choices: Array[AnyRef], defaultChoice: Any)
            (implicit parent: Container): AnyRef =
    choice(parent, title, msg, choices, defaultChoice)
}