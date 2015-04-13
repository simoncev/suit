/**
 * Copyright© Steven Dobay 2015
 */
package suit

import helpers.ArrayHelpers._

/**
 * @author Steven Dobay
 *
 * Basic abstraction for managing events and listeners
 * for components. It can reduce code easily.
 */
protected[suit] abstract class EventManager[EventType, ListenerType]() {
  private var reactors: Array[(EventType, ListenerType)] = Array()

  /**
   * Create a new listener
   * @param e
   * @return with the created listener
   */
  protected def createAndAddListener(e: EventType): ListenerType

  /**
   * Registers the new event everywhere
   * @param e
   */
  def +=(e: EventType) = {
    val listener = createAndAddListener(e)
    reactors = reactors ++ Array((e, listener))
  }

  /**
   * Removes the listener from the registration places
   * @param l
   */
  protected def removeListener(l: ListenerType): Unit

  /**
   * Removes the listeners and this event from the registration places
   * @param e
   */
  def -=(e: EventType) = {
    val fnIx = reactors.map(_._1).indexOf(e)
    if (fnIx != -1) {
      removeListener(reactors(fnIx)._2)
      reactors = reactors.removeOnIndex(fnIx).toArray
    }
  }

  /**
   * Removes all events permanently.
   */
  def makeDeafToAll() = {
    reactors.foreach(t => removeListener(t._2))
    reactors = Array()
  }

  /**
   * @return with all event methods
   */
  def getAll() = reactors.map(_._1)

  protected[suit] def getAllListeners() =
    reactors.map(_._2)
}