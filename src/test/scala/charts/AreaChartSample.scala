/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts.{Horizontal, ChartPanel, AreaChart}

/**
 * @author Steven Dobay
 */
object AreaChartSample extends DesktopApp("Sample for area chart") {
  frame.size = Size(800, 600)

  val chart = AreaChart (
    "My Bar",                    //title
    "X", "Y",                    // x- and y axis-titles
    List((10, "First", "One"),  //dataset...
      (7, "First", "Two"),
      (8, "Second", "One"),
      (6, "Second", "Two")),
    Horizontal
  )

  frame += new ChartPanel(chart)
}
