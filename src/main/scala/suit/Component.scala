/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

import java.awt.{Font, Color}
import javax.swing.JComponent
import javax.swing.border.Border

/**
 * @author Steven Dobay
 */
trait Component { self =>

  type ComponentType = self.type

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped: JComponent

  protected var containerObj: Option[Container] = None

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
   * @return with the name of the class
   */
  def className: String

  /**
   * @return
   */
  def preferredSize = wrapped.getPreferredSize

  /**
   * @param d
   */
  def preferredSize_=(d: Dim) =
    wrapped.setPreferredSize(d)

  /**
   * @param d
   */
  def withPreferredSize(d: Dim): self.type = {
    wrapped.setPreferredSize(d)
    self
  }


  def minSize = wrapped.getMinimumSize
  def maxSize = wrapped.getMaximumSize

  /**
   * @param d
   */
  def minSize_=(d: Dim) =
    wrapped.setMinimumSize(d)
  /**
   * @param d
   */
  def maxSize_=(d: Dim) =
    wrapped.setMaximumSize(d)

  def font = wrapped.getFont

  /**
   * @param f
   */
  def font_=(f: Font) = wrapped.setFont(f)

  def foreGround = wrapped.getForeground

  /**
   * @param c
   */
  def foreGround_=(c: Color) =
    wrapped.setForeground(c)

  def isVisible() = wrapped.setVisible(true)

  /**
   * Sets the component visible
   */
  def show() = wrapped.setVisible(true)

  /**
   * Hides the component
   */
  def hide() = wrapped.setVisible(false)

  def border = wrapped.getBorder

  /**
   * @param b
   */
  def border_=(b: Border) = wrapped.setBorder(b)

  def revalidate() = wrapped.revalidate()

  def repaint(l: Long) =
    wrapped.repaint(l)

  def repaint() =
    wrapped.repaint()
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
   * @param bg
   * @return with self
   */
  def withBackground(bg: Color) =
    wrapped.setBackground(bg)

  /**
   * @return with the graphics of the component
   */
  def getGraphics() = wrapped.getGraphics

  def toolTip = wrapped.getToolTipText

  /**
   * Creates tooltip for the component
   * @param t
   */
  def toolTip_=(t: String) =
    wrapped.createToolTip().setTipText(t)

  def isOpaque = wrapped.isOpaque

  /**
   * Sets the opactiy of the component
   * @param b
   */
  def setOpaque(b: Boolean) = wrapped.setOpaque(b)

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
  def withAction(proc: ActionEvent => Unit): self.type  = {
    onAction(proc)
    self
  }

  /**
   * Adds action handling to the component
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
   * Adds a popupmenu to the component
   * @param p
   */
  def popupMenu_=(p: PopupMenu) =
   wrapped.setComponentPopupMenu(p.wrapped)

  def popupMenu =
    wrapped.getComponentPopupMenu
           .getClientProperty("scala-frame-wrapper")
           .asInstanceOf[PopupMenu]

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
  def handleMousePress(e: MouseEvent => Unit): self.type  = {
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
  def handleMouseEnter(e: MouseEvent => Unit): self.type  = {
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
  def handleMouseExit(e: MouseEvent => Unit): self.type  = {
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
  def handleMouseRelease(e: MouseEvent => Unit): self.type  = {
    onMouseRelease(e)
    self
  }

  override def equals(obj: Any) =
   if(obj.isInstanceOf[self.type])
     obj.asInstanceOf[self.type].wrapped == wrapped
   else if(obj.isInstanceOf[JComponent])
     obj.asInstanceOf[JComponent] == wrapped
   else false
}