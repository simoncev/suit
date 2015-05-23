/**
 * Copyright© by Steven Dobay on 2015
 */

package org.suit

import java.net.URL
import javax.swing.JComponent
import javax.swing.event.{HyperlinkEvent => HE}
import javax.swing.text.Element

/**
 * @author Steven Dobay
 */
case class HyperLinkEvent(
 private val cSource: Component,
 private val cId: Option[Int],
 private val cWhen: Long,
 description: String,
 url: URL,
 eventType: HyperLinkEvent.EventType,
 sourceElement: Element
) extends Event(cSource, None, cWhen)

object HyperLinkEvent {
  def apply(hle: HE) =
    new HyperLinkEvent(
      hle.getSource.asInstanceOf[JComponent]
                         .getClientProperty("suit-wrapper")
                         .asInstanceOf[Component],
      None,
      System.currentTimeMillis(),
      hle.getDescription,
      hle.getURL,
      readEventType(hle.getEventType),
      hle.getSourceElement
    )

  trait EventType
  case object activated extends EventType
  case object entered extends EventType
  case object exited extends EventType

  private def readEventType(et: HE.EventType) =
    et match {
      case HE.EventType.ACTIVATED => activated
      case HE.EventType.ENTERED   => entered
      case HE.EventType.EXITED    => exited
    }
}
