/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.InputMethodEvent

/**
 * @author Steven Dobay
 */
case class EditEvent(source: Component, when: Long,
                     committedChars: Int)

object EditEvent {
  def apply(ime: InputMethodEvent) =
   new EditEvent(ime.getSource.asInstanceOf[Component],
                 ime.getWhen, ime.getCommittedCharacterCount)
}