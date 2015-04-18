/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

import java.awt.event.{FocusEvent => JFocusEvent}
import java.awt.event.{MouseMotionListener, FocusListener, MouseListener}
import java.awt.{event, Font, Color}
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
   * Manager of style classes.
   */
  object styleClasses {
    private var classes: Set[String] = Set(className)

    /**
     * Adds a new style-class.
     * @param name
     */
    def +=(name: String) =
      if(name != className) classes = classes + name

    /**
     * Removes the style-class.
     * @param name
     */
    def -=(name: String) =
      if(name != className) classes = classes - name

    /**
     * @param name
     * @return true if the style-class is already added to this component.
     */
    def contains(name: String) = classes.contains(name)
  }

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
  def preferredSize_=(d: Size) =
    wrapped.setPreferredSize(d)

  /**
   * @param d
   */
  def withPreferredSize(d: Size): self.type = {
    wrapped.setPreferredSize(d)
    self
  }


  def minSize = wrapped.getMinimumSize
  def maxSize = wrapped.getMaximumSize

  /**
   * @param d
   */
  def minSize_=(d: Size) =
    wrapped.setMinimumSize(d)
  /**
   * @param d
   */
  def maxSize_=(d: Size) =
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
           .getClientProperty("suit-wrapper")
           .asInstanceOf[PopupMenu]

  /**
   * @param x
   * @param y
   * @return true if the component contains the given point
   */
  def contains(x: Int, y: Int) = wrapped.contains(x, y)

  /**
   * Manager of mouse events
   */
  object mouseEvents extends EventManager[MouseHandler, MouseListener] {
    protected def removeListener(l: MouseListener): Unit =
      wrapped.removeMouseListener(l)

    protected def createAndAddListener(h: MouseHandler): MouseListener = {
       val listener = h.create
       wrapped.addMouseListener(listener)
       listener
    }
  }

   /**
    * Manager of focus events.
    */
  object focusEvents extends EventManager[FocusEvent => Unit, FocusListener] {
    protected def removeListener(l: FocusListener) =
     wrapped.removeFocusListener(l)

    protected def createAndAddListener(proc: FocusEvent => Unit) = {
      val l = new FocusListener {
        override def focusGained(e: JFocusEvent): Unit =
          proc(FocusEvent(e).copy(focusGained = true))
        override def focusLost(e: JFocusEvent): Unit =
          proc(FocusEvent(e))
      }
      wrapped.addFocusListener(l)
      l
    }
  }

  /**
   * Manager of mouse-motions
   */
  object mouseMotions
    extends EventManager[MouseMotionEvent => Unit, MouseMotionListener] {
     protected def createAndAddListener(proc: MouseMotionEvent => Unit) = {
       val l = new MouseMotionListener {
        override def mouseMoved(e: event.MouseEvent): Unit =
          proc(MouseMotionEvent(e, true))
        override def mouseDragged(e: event.MouseEvent): Unit =
          proc(MouseMotionEvent(e, false))
       }
       wrapped.addMouseMotionListener(l)
       l
     }

    protected def removeListener(l: MouseMotionListener) =
     wrapped.removeMouseMotionListener(l)
  }

  override def equals(obj: Any) =
   if(obj.isInstanceOf[self.type])
     obj.asInstanceOf[self.type].wrapped == wrapped
   else if(obj.isInstanceOf[JComponent])
     obj.asInstanceOf[JComponent] == wrapped
   else false
}