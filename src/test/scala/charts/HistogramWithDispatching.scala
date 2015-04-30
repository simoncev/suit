/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit.{DesktopApp, Size}
import org.suit.charts.{HistogramData, Horizontal, ChartPanel, Histogram_}

/**
 * @author Steven Dobay
 */
object HistogramWithDispatching
 extends DesktopApp("Sample for histogram with dispatching") {

  frame.size = Size(800, 600)

  val hg = new Histogram_("Sample") {
    xAxisTitle := "X"
    yAxisTitle := "Y"
    orientation := Horizontal

    dataSet += HistogramData("A",
      Array(Array(1, 2), Array(3, 4), Array(5, 6), Array(7, 8),
            Array(9, 10), Array(11, 12)))
    dataSet += HistogramData("B",
      Array(Array(2, 3), Array(4, 5), Array(6, 7), Array(8, 9),
            Array(10, 11), Array(12, 13)))
  }

  frame += ChartPanel(hg)
}
