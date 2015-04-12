/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.InputMethodEvent

/**
 * @author Steven Dobay
 */
case class EditEvent(private val cSource: Component,
                     private val cId: Int,
                     private val cWhen: Long,
                     committedChars: Int,
                     caretJustMoved: Boolean,
                     caretPosition: Int)
 extends ChangeEvent(cSource, cId, cWhen, committedChars)

object EditEvent {
  def apply(ime: InputMethodEvent, caretMoved: Boolean) =
   new EditEvent(ime.getSource.asInstanceOf[Component],
                 ime.getID,
                 ime.getWhen, ime.getCommittedCharacterCount,
                 caretMoved, ime.getCaret.getCharIndex)
}