/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JProgressBar
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class ProgressBar(private val lowerBound: Int = 0,
                       private val upperBound: Int = 100)
   extends Widget with Bindable[Int] {

  private val bar = new JProgressBar()

  bar.setValue(lowerBound)
  bar.setMinimum(lowerBound)
  bar.setMaximum(upperBound)

  def value = bar.getValue
  def value_=(barValue: Int) = bar.setValue(barValue)

  def min = bar.getMinimum
  def max = bar.getMaximum

  def min_=(m: Int) = bar.setMinimum(m)
  def max_=(m: Int) = bar.setMaximum(m)

  def onChange(v: HolderOf[Int]) = {
    bar.addChangeListener(new ChangeListener {
      override def stateChanged(e: ChangeEvent): Unit =
       v.value = value
    })
  }

  protected[suit] def wrapped = bar
  def className = "ProgressBar"
}
