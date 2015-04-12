/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * Common abstraction for all events.
 */
abstract class Event(val source: Component, val id: Int, val when: Long)
