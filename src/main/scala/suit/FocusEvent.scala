/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent
import helpers.ComponentHelpers._

/**
 * @author Steven Dobay
 */
case class FocusEvent(private val cSource: Component,
                      private val cId: Int,
                      private val cWhen: Long,
                      focusGained: Boolean)

object FocusEvent {
  def apply(e: java.awt.event.FocusEvent): FocusEvent =
    FocusEvent(e.getSource.asInstanceOf[JComponent].readSuitComponent,
               e.getID, -1L, false)
}
