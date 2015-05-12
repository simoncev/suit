/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 * Common abstraction for all events.
 */
abstract class Event(val source: Component, val id: Option[Int], val when: Long)
