/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit.charts.{ChartPanel, RingChart_}
import org.suit.{Size, DesktopApp}

/**
 * @author Steven Dobay
 */
object RingChartWithDispatching
  extends DesktopApp("Sample for ring chart with dispatching") {

  frame.size = Size(800, 600)


  val pie = new RingChart_[Int]("Candy market") {
    dataSet += ("Chocolates", 10)
    dataSet += ("Biscuits", 7)
    dataSet += ("Cakes", 5)
  }

  frame += ChartPanel(pie)
}
