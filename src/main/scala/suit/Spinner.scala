/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.{event, JSpinner}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class Spinner()
   extends Widget with Bindable[Int] with Stateful[Int] {

  private val spinner = new JSpinner()

  spinner.putClientProperty("scala-frame-wrapper", this)

  def value: Int = spinner.getValue.asInstanceOf[Int]
  def value_=(v: Int) = spinner.setValue(v)

  protected def onChangeDoBind(v: HolderOf[Int]) = {
    spinner.addChangeListener(new ChangeListener {
      override def stateChanged(e: event.ChangeEvent): Unit =
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
    spinner.addChangeListener(listener)
    listener
  }

  protected def removeListener(l: ListenerType) =
   spinner.removeChangeListener(l)

  protected[suit] def wrapped = spinner

  def className = "Spinner"
}
