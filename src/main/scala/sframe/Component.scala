/**
 * Copyright© Steven Dobay 2015
 */
package sframe

import java.awt.event.MouseListener
import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
trait Component { self =>
  /**
   * @return with a pointer to the wrapped JComponent
   */
  def getWrapped: JComponent

  /**
   * @return with a pointer to the parent optionally
   */
  def getContainer: Option[Container]

  /**
   * Sets the parent of the Component
   * @param c
   */
  def setContainer(c: Container): Unit

  /**
   * @return with the name of the class
   */
  def className: String

  /**
   * Adds action handling to the component
   * @param proc
   */
  def onAction(proc: ActionEvent => Unit): Unit = {}

  /**
   * Adds action handling to the component
   * @param proc
   * @return with the component's pointer
   */
  def withAction(proc: ActionEvent => Unit): Component = {
    onAction(proc)
    self
  }

  /**
   * Adds action handling to the component
   * @param proc
   * @return with the component's pointer
   */
  def @> = withAction _

  /**
   * @param key
   */
  def property(key: String): AnyRef

  /**
   * @param key
   * @param value
   */
  def property_=(key: String, value: AnyRef): Unit

  /**
   * Handles the click actions
   * @param e
   */
  def onClick(e: MouseEvent => Unit): Unit =
    getWrapped.addMouseListener(new MouseHandler().handleClick(e).create)

  /**
   * Handles the click actions
   * @param e
   * @return with itself
   */
  def handleClick(e: MouseEvent => Unit) = {
    onClick(e)
    self
  }

  /**
   * Handles the press actions
   * @param e
   */
  def onMousePress(e: MouseEvent => Unit): Unit =
    getWrapped.addMouseListener(new MouseHandler().handlePress(e).create)

  /**
   * Handles the mousepress actions
   * @param e
   * @return with itself
   */
  def handleMousePress(e: MouseEvent => Unit) = {
    onMousePress(e)
    self
  }

  /**
   * Handles the enter actions
   * @param e
   */
  def onMouseEnter(e: MouseEvent => Unit): Unit =
    getWrapped.addMouseListener(new MouseHandler().handleEnter(e).create)

  /**
   * Handles the mouse-enter actions
   * @param e
   * @return with itself
   */
  def handleMouseEnter(e: MouseEvent => Unit) = {
    onMouseEnter(e)
    self
  }

  /**
   * Handles the exit actions
   * @param e
   */
  def onMouseExit(e: MouseEvent => Unit): Unit =
    getWrapped.addMouseListener(new MouseHandler().handleExit(e).create)

  /**
   * Handles the mouse-exit actions
   * @param e
   * @return with itself
   */
  def handleMouseExit(e: MouseEvent => Unit) = {
    onMouseExit(e)
    self
  }

  /**
   * Handles the release actions
   * @param e
   */
  def onMouseRelease(e: MouseEvent => Unit): Unit =
    getWrapped.addMouseListener(new MouseHandler().handleRelease(e).create)

  /**
   * Handles the mouse-release actions
   * @param e
   * @return with itself
   */
  def handleMouseRelease(e: MouseEvent => Unit) = {
    onMouseRelease(e)
    self
  }
}
