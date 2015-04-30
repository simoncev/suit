/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts.{BubbleData, ChartPanel, BubbleChart_}

/**
 * @author Steven Dobay
 */
object BubbleChartWithDispatching
  extends DesktopApp("Sample for bubble chart with dispatching") {

  frame.size = Size(800, 600)

  val chart = new BubbleChart_("Bubbles!") {
    xAxisTitle := "X"
    yAxisTitle := "Y"

    dataSet += BubbleData("A", Array(Array(10, 20, 30),
                                     Array(10, 5, 2),
                                     Array(4, 5, 6)))
  }

  frame += ChartPanel(chart)

}
