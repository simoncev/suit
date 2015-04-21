/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package org.suit

import java.awt.event
import java.awt.event.MouseListener

/**
 * @author Steven Dobay
 */
class MouseHandler {
  private var onClick: (MouseEvent => Unit) = (_ => ())
  private var onExit: (MouseEvent => Unit) = (_ => ())
  private var onEnter: (MouseEvent => Unit) = (_ => ())
  private var onPress: (MouseEvent => Unit) = (_ => ())
  private var onRelease: (MouseEvent => Unit) = (_ => ())

  /**
   * Adds click-handler
   * @param e
   * @return with this
   */
  def handleClick(e: MouseEvent => Unit) = {
    onClick = e
    this
  }

  /**
   * Adds exit-handler
   * @param e
   * @return with this
   */
  def handleExit(e: MouseEvent => Unit) = {
    onExit = e
    this
  }

  /**
   * Adds enter-handler
   * @param e
   * @return with this
   */
  def handleEnter(e: MouseEvent => Unit) = {
    onEnter = e
    this
  }

  /**
   * Adds press-handler
   * @param e
   * @return with thisn
   */
  def handlePress(e: MouseEvent => Unit) = {
    onPress = e
    this
  }

  /**
   * Adds release-handler
   * @param e
   * @return with this
   */
  def handleRelease(e: MouseEvent => Unit) = {
    onRelease = e
    this
  }

  /**
   * @return with a new mouselistener where the methods
   *         are initialized with the given handlers
   */
  protected[suit] def create = new MouseListener {
    override def mouseExited(e: event.MouseEvent): Unit =
      onExit(MouseEvent(e))
    override def mouseClicked(e: event.MouseEvent): Unit =
      onClick(MouseEvent(e))
    override def mouseEntered(e: event.MouseEvent): Unit =
      onEnter(MouseEvent(e))
    override def mousePressed(e: event.MouseEvent): Unit =
      onPress(MouseEvent(e))
    override def mouseReleased(e: event.MouseEvent): Unit =
      onRelease(MouseEvent(e))
  }
}
