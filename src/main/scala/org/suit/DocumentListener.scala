/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 *
 * A different DocumentListener for org.suit to handle
 * document-events better.
 */
protected[suit] abstract class DocumentListener
  extends javax.swing.event.DocumentListener {

  private var tm: Long = 0L

  /**
   * Buffers the time in Long.
   */
  def readTime() = tm = System.currentTimeMillis()

  /**
   * @return with the event's component
   */
  def component(): Component

  /**
   * @return with the (moment)millis of the event
   */
  def when(): Long = tm

  /**
   * @param newChars
   * @param caretPos
   * @return with a new edit event data class
   */
  def createEditEvent(newChars: Int, caretPos: Int) = {
    readTime()
    EditEvent(component(), None, when(), Some(newChars),
              caretPos, false, false, false)
  }
}
