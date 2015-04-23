/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.{event, JSlider}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class Slider(private val initMin: Int = 0,
                  private val initMax: Int = 100)
   extends Bindable[Int] with Widget {

  type Wrapped = JSlider

  private val slider = new JSlider(initMin, initMax)

  slider.putClientProperty ("suit-wrapper", this)
  slider.setValue(initMin)

  def min = slider.getMinimum
  def max = slider.getMaximum

  def min_=(m: Int) = slider.setMinimum(m)
  def max_=(m: Int) = slider.setMaximum(m)

  def value: Int = slider.getValue
  def value_=(v: Int) = slider.setValue(v)

  protected[suit] def wrapped = slider

  def className = "Slider"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ChangeEvent
  protected type ChangeListenerType = ChangeListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def stateChanged(e: event.ChangeEvent): Unit =
        proc(ChangeEvent(e))
    }
    slider.addChangeListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    slider.removeChangeListener(l)

  def componentValue() = value
}
