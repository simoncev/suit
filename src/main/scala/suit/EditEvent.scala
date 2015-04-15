/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.InputMethodEvent
import javax.swing.JComponent
import javax.swing.event.DocumentEvent
import helpers.ComponentHelpers._

/**
 * @author Steven Dobay
 */
case class EditEvent(private val cSource: Component,
                     private val cId: Option[Int],
                     private val cWhen: Long,
                     committedChars: Option[Long],
                     caretPosition: Int,
                     doneInsertion: Boolean,
                     doneChange: Boolean,
                     doneRemoval: Boolean)
 extends ChangeEvent(cSource, cId, cWhen, committedChars)

/*object EditEvent {
  def apply(ime: InputMethodEvent) =
   new EditEvent(ime.getSource.asInstanceOf[JComponent].readSuitComponent,
                 Some(ime.getID),
                 ime.getWhen, Some(ime.getCommittedCharacterCount),
                 ime.getCaret.getCharIndex, false, true, false)
}*/