/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 * @param cSource
 * @param cId
 * @param cWhen
 * @param cX
 * @param cY
 * @param cClickCount
 * @param cIsPopupTrigger
 * @param preciseWheelRotation
 * @param scrollAmount
 * @param isAltHolded
 * @param isAltGraphHolded
 * @param isControlHolded
 * @param isShiftHolded
 */
case class MouseWheelEvent(
 private val cSource: Component,
 private val cId: Option[Int],
 private val cWhen: Long,
 private val cX: Int, private val cY: Int,
 private val cClickCount: Int,
 private val cIsPopupTrigger: Boolean,
 preciseWheelRotation: Double,
 scrollAmount: Int,
 isAltHolded: Boolean,
 isAltGraphHolded: Boolean,
 isControlHolded: Boolean,
 isShiftHolded: Boolean
) extends MouseEvent(cSource, cId, cWhen, cX, cY, cClickCount, cIsPopupTrigger)

object MouseWheelEvent {
  import helpers.ComponentHelpers._

  /**
   * Converts a java.awt.event.MouseWheelEvent
   * to an org.suit.MouseWheelEvent.
   * @param e
   * @return with a new MouseWheelEvent.
   */
  def apply(e: java.awt.event.MouseWheelEvent): MouseWheelEvent =
   MouseWheelEvent(e.getSource.asInstanceOf[JComponent].readSuitComponent,
                   Some(e.getID), e.getWhen, e.getX, e.getY,
                   e.getUnitsToScroll, e.isPopupTrigger,
                   e.getPreciseWheelRotation, e.getScrollAmount,
                   e.isAltDown, e.isAltGraphDown, e.isControlDown,
                   e.isShiftDown)
}