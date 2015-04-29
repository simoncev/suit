/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.Color
import java.beans.{PropertyChangeEvent, PropertyChangeListener}
import javax.swing.{JComponent, JColorChooser}

/**
 * @author Steven Dobay
 *
 * Simple dialog to choose colors.
 */
case class ColorChooser(private val initColor: Color = Color.WHITE)
   extends Bindable[Color]{

  private val chooser = new JColorChooser()

  chooser.putClientProperty("suit-wrapper", this)

  chooser.setColor(initColor)

  /**
   * @return with the container where it can be seen.
   */
  def previewIn = chooser.getPreviewPanel
                         .getClientProperty("suit-wrapper")
                         .asInstanceOf[Container]

  /**
   * Sets the preview panel.
   * @param c
   */
  def previewIn_=(c: Container) =
   chooser.setPreviewPanel(c.wrappedContainer.asInstanceOf[JComponent])

  /**
   * @return with the default color.
   */
  def color = chooser.getColor

  /**
   * Sets the default color.
   * @param c
   */
  def color_=(c: Color) = chooser.setColor(c)

  /**
   * Sets the default color by an integer
   * @param c
   */
  def color_=(c: Int) = chooser.setColor(c)

  /**
   * Sets the default color by rgb
   * @param rgb
   */
  def color_=(rgb: (Int, Int, Int)) =
    chooser.setColor(rgb._1, rgb._2, rgb._3)

  protected def setValue(v: Color) = color = v

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = chooser

  /**
   * @return with the name of the class
   */
  def className = "ColorChooser"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ChangeEvent
  protected type ChangeListenerType = PropertyChangeListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new PropertyChangeListener {
      override def propertyChange(e: PropertyChangeEvent): Unit =
        proc(ChangeEvent.apply(e.getSource.asInstanceOf[JComponent]
                                .getClientProperty("suit-wrapper")
                                .asInstanceOf[Component],
                               None, System.currentTimeMillis(),
                               Some(1)))
    }
    chooser.addPropertyChangeListener("color", listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    chooser.removePropertyChangeListener(l)

  def componentValue() = color
}

object ColorChooser {
  /**
   * @param r
   * @param g
   * @param b
   * @return with a new color chooser with the given rgb color
   *         as default
   */
  def apply(r: Int, g: Int, b: Int) = {
    val ch = new ColorChooser()
    ch.color = (r, g, b)
    ch
  }

  /**
   * @param code
   * @return with a new color chooser with the given hex color
   *         as default
   */
  def apply(code: Int) = {
    val ch = new ColorChooser()
    ch.color = code
    ch
  }
}