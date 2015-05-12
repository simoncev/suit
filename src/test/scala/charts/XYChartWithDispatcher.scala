/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object XYChartWithDispatcher extends DesktopApp("XY chart with dispatching") {

  frame.size = Size(800, 600)

  val chart = new XYChart_[Int]("My XY") {
    xTitle := "X"
    yTitle := "Y"
    dataSet += XYSeries("Evens",
                   List((1, 2), (2, 4), (3, 6), (4, 8)))
    dataSet += XYSeries("Primes",
                   List((1, 2), (2, 3), (3, 5), (4, 7)))
    dataSet += XYSeries("Odds",
                   List((1, 1), (2, 3), (3, 5), (4, 7)))
  }

  frame += ChartPanel(chart)
}
