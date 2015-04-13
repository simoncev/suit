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
   extends Bindable[Color]{

  private val chooser = new JColorChooser()

  chooser.setColor(initColor)

  def color = chooser.getColor

  def color_=(c: Color) = chooser.setColor(c)

  def color_=(c: Int) = chooser.setColor(c)
  def color_=(rgb: (Int, Int, Int)) =
    chooser.setColor(rgb._1, rgb._2, rgb._3)

  protected[suit] def wrapped = chooser
  def className = "ColorChooser"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ChangeEvent
  protected type ChangeListenerType = PropertyChangeListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new PropertyChangeListener {
      override def propertyChange(e: PropertyChangeEvent): Unit =
        proc(ChangeEvent(e.getSource.asInstanceOf[JComponent]
                               .getClientProperty("suit-wrapper")
                               .asInstanceOf[Component],
                    -1, -1, 1))
    }
    chooser.addPropertyChangeListener("color", listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    chooser.removePropertyChangeListener(l)

  def componentValue() = color
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