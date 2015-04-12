/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Color
import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.swing.{JComponent, JColorChooser}

/**
 * @author Steven Dobay
 */
case class ColorChooser(private val initColor: Color = Color.WHITE)
   extends Component with Bindable[Color] with Stateful[Color] {

  private val chooser = new JColorChooser()

  chooser.setColor(initColor)

  def color = chooser.getColor

  def color_=(c: Color) = chooser.setColor(c)

  def color_=(c: Int) = chooser.setColor(c)
  def color_=(rgb: (Int, Int, Int)) =
    chooser.setColor(rgb._1, rgb._2, rgb._3)

  protected def onChangeDoBind(h: HolderOf[Color]) =
     changeEvents += (_ => h.value = color)

  protected type EventType = ChangeEvent
  protected type ListenerType = PropertyChangeListener

  protected def createAndGetListener(proc: EventType => Unit) = {
    val listener = new PropertyChangeListener {
      override def propertyChange(e: PropertyChangeEvent): Unit =
        proc(ChangeEvent(e.getSource.asInstanceOf[JComponent]
                               .getClientProperty("scala-frame-wrapper")
                               .asInstanceOf[Component],
                    -1, -1, 1))
    }
    chooser.addPropertyChangeListener("color", listener)
    listener
  }

  protected def removeListener(l: ListenerType) =
    chooser.removePropertyChangeListener(l)

  protected[suit] def wrapped = chooser
  def className = "ColorChooser"
}

object ColorChooser {
  def apply(r: Int, g: Int, b: Int) = {
    val ch = new ColorChooser()
    ch.color = (r, g, b)
    ch
  }

  def apply(code: Int) = {
    val ch = new ColorChooser()
    ch.color = code
    ch
  }
}