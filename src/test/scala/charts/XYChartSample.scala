/**
 * Copyright© Steven Dobay 2015
 */
package charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object XYChartSample extends DesktopApp("XY chart sample") {
  val chart = XYChart[Int]("My XY", "X", "Y") // chart's title with the titles of x and y axises
    .withSeries("Evens",
                List((1, 2), (2, 4), (3, 6), (4, 8))) //datasets...
    .withSeries("Primes",
                List((1, 2), (2, 3), (3, 5), (4, 7)))
    .withSeries("Odds",
                List((1, 1), (2, 3), (3, 5), (4, 7)))
    .createChart() // creating the chart with the given datas

  frame.size = Size(800, 600)

  frame += new ChartPanel(chart)
}
