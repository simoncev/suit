/**
 * Copyright© Steven Dobay 2015
 */
package sframe

import java.awt.{Font, Color}
import javax.swing.JComponent
import javax.swing.border.Border

/**
 * @author Steven Dobay
 */
trait Component { self =>
  protected var containerObj: Option[Container] = None

  /**
   * @return with a pointer to the wrapped JComponent
   */
  def wrapped: JComponent

  /**
   * @return with a pointer to the parent optionally
   */
  def container: Option[Container] = containerObj

  /**
   * Sets the parent of the Component
   * @param c
   */
  def container_=(c: Container): Unit =
    containerObj = Some(c)


  /**
   * @return
   */
  def preferredSize = wrapped.getPreferredSize

  /**
   * @param d
   */
  def preferredSize_=(d: Dim) = wrapped.setPreferredSize(d)


  def minSize = wrapped.getMinimumSize
  def maxSize = wrapped.getMaximumSize

  /**
   * @param d
   */
  def minSize_=(d: Dim) = wrapped.setMinimumSize(d)

  /**
   * @param d
   */
  def maxSize_=(d: Dim) = wrapped.setMaximumSize(d)

  def font = wrapped.getFont

  /**
   * @param f
   */
  def font_=(f: Font) = wrapped.setFont(f)

  def foreGround = wrapped.getForeground

  /**
   * @param c
   */
  def foreGround_=(c: Color) = wrapped.setForeground(c)

  def isVisible() = wrapped.setVisible(true)

  /**
   * Sets the component visible
   */
  def show() = wrapped.setVisible(true)

  /**
   * Hides the component
   */
  def hide() = wrapped.setVisible(false)

  def border() = wrapped.getBorder

  /**
   * @param b
   */
  def border(b: Border) = wrapped.setBorder(b)

  def revalidate() = wrapped.revalidate()

  def repaint(l: Long) = wrapped.repaint(l)
  def repaint() = wrapped.repaint()

  /**
   * @return with the component's background
   */
  def background = wrapped.getBackground

  /**
   * Sets the component's background
   * @param background
   */
  def background_=(background: Color) =
    wrapped.setBackground(background)

  /**
   * @return with the graphics of the component
   */
  def getGraphics() = wrapped.getGraphics

  def toolTip() = wrapped.getToolTipText

  /**
   * Creates tooltip for the component
   * @param t
   */
  def toolTip_=(t: String) = wrapped.createToolTip().setTipText(t)

  def isOpaque = wrapped.isOpaque

  /**
   * Sets the opactiy of the component
   * @param b
   */
  def setOpaque(b: Boolean) = wrapped.setOpaque(b)

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
   * @return with the property at the given key
   */
  def property(key: String) = wrapped.getClientProperty(key)

  /**
   * Puts the property to the client property
   */
  def property_=(key: String, value: AnyRef) =
    wrapped.putClientProperty(key, value)


  /**
   * @param x
   * @param y
   * @return true if the component contains the given point
   */
  def contains(x: Int, y: Int) = wrapped.contains(x, y)

  /**
   * Handles the click actions
   * @param e
   */
  def onClick(e: MouseEvent => Unit): Unit =
    wrapped.addMouseListener(new MouseHandler().handleClick(e).create)

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
    wrapped.addMouseListener(new MouseHandler().handlePress(e).create)

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
    wrapped.addMouseListener(new MouseHandler().handleEnter(e).create)

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
    wrapped.addMouseListener(new MouseHandler().handleExit(e).create)

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
    wrapped.addMouseListener(new MouseHandler().handleRelease(e).create)

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