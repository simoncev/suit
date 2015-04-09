/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JSlider

/**
 * @author Steven Dobay
 */
case class Slider(private val initMin: Int = 0,
                  private val initMax: Int = 100) extends Widget {
  private val slider = new JSlider(initMin, initMax)

  slider.putClientProperty ("scala-frame-wrapper", this)
  slider.setValue(initMin)

  def min = slider.getMinimum
  def max = slider.getMaximum

  def min_=(m: Int) = slider.setMinimum(m)
  def max_=(m: Int) = slider.setMaximum(m)

  def value = slider.getValue
  def value_=(v: Int) = slider.setValue(v)

  def wrapped = slider

  def className = "Slider"
}
