/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JProgressBar

/**
 * @author Steven Dobay
 */
case class ProgressBar(private val lowerBound: Int = 0,
                       private val upperBound: Int = 100,
                       private val defaultValue: Int = 0)
   extends Widget {

  private val bar = new JProgressBar()
  bar.setValue(defaultValue)

  bar.putClientProperty("suit-wrapper", this)

  bar.setValue(lowerBound)
  bar.setMinimum(lowerBound)
  bar.setMaximum(upperBound)

  /**
   * @return with the progress bar's value.
   */
  def value = bar.getValue

  /**
   * Sets the progress bar's value(default: 0).
   * @param barValue
   */
  def value_=(barValue: Int) = bar.setValue(barValue)

  /**
   * Maps the progress bar's value.
   * @param fn
   */
  def map(fn: Int => Int) = bar.setValue(fn(bar.getValue))

  /**
   * @return with the minimum value of the bar,
   */
  def min = bar.getMinimum

  /**
   * @return with the maximum value of the bar.
   */
  def max = bar.getMaximum

  /**
   * Sets the bar's minimum value(default: 0).
   * @param m
   */
  def min_=(m: Int) = bar.setMinimum(m)

  /**
   * Sets the bar's maximum value(default: 100).
   * @param m
   */
  def max_=(m: Int) = bar.setMaximum(m)

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = bar

  /**
   * @return with the name of the class
   */
  def className = "ProgressBar"
}
