/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JSlider
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class Slider(private val initMin: Int = 0,
                  private val initMax: Int = 100)
   extends Widget with Bindable[Int] {

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

  protected def onChange(v: HolderOf[Int]) = {
    slider.addChangeListener(new ChangeListener {
      override def stateChanged(e: ChangeEvent): Unit =
        v.value = value
    })
  }

  protected[suit] def wrapped = slider

  def className = "Slider"
}
