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

  protected type ME = MouseEvent => Unit

  def pack(): Component

  val preferredSize = Property[Dim](pack.preferredSize = _)
  val minSize = Property[Dim](pack.minSize = _)
  val maxSize = Property[Dim](pack.maxSize = _)

  val font = Property[Font](pack.font = _)

  val foreGround = Property[Color](pack.foreGround = _)

  val visible = Property[Boolean](b => if(b) pack.show else pack.hide)

  val border = Property[Border](pack.border = _)

  val background = Property[Color](pack.background = _)

  val toolTip = Property[String](pack.toolTip = _)

  val opaque = Property[Boolean](pack.setOpaque(_))

  val property = Property[(String, AnyRef)](t => pack.property_=(t._1, t._2))

  val popupMenu = Property[PopupMenu](pack.popupMenu = _)

  val onAction = Property[ActionEvent => Unit](pack.onAction(_))
  val onClick = Property[ME](pack.handleClick(_))
  val onMousePress = Property[ME](pack.handleMousePress(_))
  val onMouseEnter = Property[ME](pack.handleMouseEnter(_))
  val onMouseExit = Property[ME](pack.handleMouseExit(_))
  val onMouseRelease = Property[ME](pack.handleMouseRelease(_))
}
