/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JSpinner
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class Spinner()
   extends Widget with Bindable[Int] {

  private val spinner = new JSpinner()

  spinner.putClientProperty("scala-frame-wrapper", this)

  def value: Int = spinner.getValue.asInstanceOf[Int]
  def value_=(v: Int) = spinner.setValue(v)

  protected def onChange(v: HolderOf[Int]) = {
    spinner.addChangeListener(new ChangeListener {
      override def stateChanged(e: ChangeEvent): Unit =
      v.value = value
    })
  }

  protected[suit] def wrapped = spinner

  def className = "Spinner"
}
