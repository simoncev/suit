/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JProgressBar

/**
 * @author Steven Dobay
 */
case class ProgressBar(private val lowerBound: Int = 0,
                       private val upperBound: Int = 100)
   extends Widget {

  private val bar = new JProgressBar()

  bar.putClientProperty("suit-wrapper", this)

  bar.setValue(lowerBound)
  bar.setMinimum(lowerBound)
  bar.setMaximum(upperBound)

  def value = bar.getValue
  def value_=(barValue: Int) = bar.setValue(barValue)

  def min = bar.getMinimum
  def max = bar.getMaximum

  def min_=(m: Int) = bar.setMinimum(m)
  def max_=(m: Int) = bar.setMaximum(m)

  protected[suit] def wrapped = bar
  def className = "ProgressBar"
}
