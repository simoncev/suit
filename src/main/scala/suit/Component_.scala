/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.{Color, Font}
import javax.swing.border.Border

/**
 * @author Steven Dobay
 * For properties' meaning go to Component
 */
trait Component_ { self =>

  def pack(): Component

  val preferredSize = Property[Dim](pack.preferredSize = _)
  val minSize = Property[Dim](pack.minSize = _)
  val maxSize = Property[Dim](pack.maxSize = _)

  val font = Property[Font](pack.font = _)

  val foreGround = Property[Color](pack.foreGround = _)

  val visible =
    Property[Boolean](b => if(b) pack.show else pack.hide)

  val border = Property[Border](pack.border = _)

  val background = Property[Color](pack.background = _)

  val toolTip = Property[String](pack.toolTip = _)

  val opaque = Property[Boolean](pack.setOpaque(_))

  val property =
    Property[(String, AnyRef)](t => pack.property_=(t._1, t._2))

  val popupMenu = Property[PopupMenu](pack.popupMenu = _)

  val onFocus = Property[FocusEvent => Unit](pack.focusEvents += _)

  val onMouseEvent = Property[MouseHandler](pack.mouseEvents += _)

  val onMouseMotionEvent =
    Property[MouseMotionEvent => Unit](pack.mouseMotions += _)
}
