/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JSpinner

/**
 * @author Steven Dobay
 */
case class Spinner() extends Widget {
  private val spinner = new JSpinner()

  spinner.putClientProperty("scala-frame-wrapper", this)

  def value = spinner.getValue
  def value_=(v: Int) = spinner.setValue(v)

  def wrapped = spinner

  def className = "Spinner"
}
