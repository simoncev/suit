/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts.{AreaChart_, Horizontal, ChartPanel, AreaChart}

/**
 * @author Steven Dobay
 */
object AreaChartWithDispatching extends DesktopApp("Sample for area chart") {
  frame.size = Size(800, 600)

  val chart = new AreaChart_[Int]("My Bar") {
    categoryTitle := "X"
    valueTitle := "Y"
    orientation := Horizontal

    dataSet += (10, "First", "One")
    dataSet += (7, "First", "Two")
    dataSet += (8, "Second", "One")
    dataSet += (6, "Second", "Two")
  }

  frame += ChartPanel(chart)
}
