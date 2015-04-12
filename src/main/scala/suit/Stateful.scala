/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * A common interface for simple state holders.
 */
trait Stateful[StateType] extends Component {

  protected type EventType <: ChangeEvent
  protected type ListenerType

  protected var reactors: Array[(EventType => Unit, ListenerType)] = Array()

  /**
   * Creates a listener, adds it to the JComponent
   * and returns with it.
   * @param proc
   * @return
   */
  protected def createAndGetListener(proc: EventType => Unit): ListenerType

  /**
   * Removes the JComponent's listener
   * @param l
   */
  protected def removeListener(l: ListenerType): Unit

  object changeEvents {
    /**
     * @param proc
     */
    def +=(proc: EventType => Unit): Unit =
      reactors = Array.concat(reactors,
        Array((proc, createAndGetListener(proc))))

    /**
     * @param proc
     */
    def -=(proc: EventType => Unit): Unit = {
      val fnIx: Int = reactors.map(_._1).indexOf(proc)
      if(fnIx != -1) {
        removeListener(reactors(fnIx)._2)
        reactors = reactors.take(fnIx) ++
                   reactors.drop(fnIx + 1)
      }
    }

    /**
     * @return with the change events' methods
     */
    def getAll() = reactors.map(_._1)

    /**
     * Removes all listeners for events and removes it
     * permanently.
     */
    def makeDeafToAll() = {
      reactors.foreach(r => removeListener(r._2))
      reactors = Array()
    }
  }
}