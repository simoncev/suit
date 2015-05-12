/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.plot.PlotOrientation

/**
 * @author Steven Dobay
 *
 * Representation for charts' orientations
 */
trait Orientation {
  def get() = this match {
    case Vertical => PlotOrientation.VERTICAL
    case _        => PlotOrientation.HORIZONTAL
  }
}
final object Vertical extends Orientation
final object Horizontal extends Orientation