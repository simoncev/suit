/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.{event, JSpinner}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class Spinner()
   extends Bindable[Int] with Widget {

  private val spinner = new JSpinner()

  spinner.putClientProperty("suit-wrapper", this)

  def value: Int = spinner.getValue.asInstanceOf[Int]
  def value_=(v: Int) = spinner.setValue(v)

  protected[suit] def wrapped = spinner

  def className = "Spinner"

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
    spinner.addChangeListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
   spinner.removeChangeListener(l)

  def componentValue() = value
}
