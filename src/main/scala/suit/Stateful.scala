/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * A common interface for simple state holders.
 */
trait Stateful[StateType] extends Component {

  protected type ChangeEventType <: ChangeEvent
  protected type ChangeListenerType

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit)
  : ChangeListenerType

  protected def removeChangeListener(l: ChangeListenerType): Unit

  /**
   * Manager of change events.
   */
  object changeEvents extends EventManager[ChangeEventType => Unit,
                                           ChangeListenerType] {

     protected def createAndAddListener(proc: ChangeEventType => Unit) =
      createAndAddChangeListener(proc)

     protected def removeListener(l: ChangeListenerType) =
      removeChangeListener(l)
  }
}