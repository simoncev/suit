/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.{event, JSlider}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class Slider(private val initMin: Int = 0,
                  private val initMax: Int = 100)
   extends Widget with Bindable[Int] with Stateful[Int] {

  type Wrapped = JSlider

  private val slider = new JSlider(initMin, initMax)

  slider.putClientProperty ("scala-frame-wrapper", this)
  slider.setValue(initMin)

  def min = slider.getMinimum
  def max = slider.getMaximum

  def min_=(m: Int) = slider.setMinimum(m)
  def max_=(m: Int) = slider.setMaximum(m)

  def value = slider.getValue
  def value_=(v: Int) = slider.setValue(v)

  protected def onChangeDoBind(v: HolderOf[Int]) = {
    slider.addChangeListener(new ChangeListener {
      override def stateChanged(e: javax.swing.event.ChangeEvent): Unit =
        v.value = value
    })
  }

  protected type EventType = ChangeEvent
  protected type ListenerType = ChangeListener

  protected def createAndGetListener(proc: EventType => Unit) = {
   val listener = new ListenerType {
     override def stateChanged(e: event.ChangeEvent): Unit =
      proc(ChangeEvent(e))
   }
    slider.addChangeListener(listener)
    listener
  }

  protected def removeListener(l: ListenerType) =
   slider.removeChangeListener(l)

  protected[suit] def wrapped = slider

  def className = "Slider"
}
