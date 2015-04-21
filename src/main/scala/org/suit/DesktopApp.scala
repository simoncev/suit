/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package org.suit

import javax.swing.UIManager

import javax.swing.plaf.nimbus.NimbusLookAndFeel

/**
 * @author Steven Dobay
 */
abstract class DesktopApp(private val initTitle: String) {
  UIManager.setLookAndFeel(new NimbusLookAndFeel())

  val frame = new Frame(initTitle)
  var arguments = Array[String]()

  implicit val popupContainer = frame

  def onStart(): Unit = {}

  def main(args: Array[String]) = {
    frame.show
    arguments = args
    onStart
  }

}
