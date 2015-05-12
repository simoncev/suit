/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit.charts.{ScatterData, Horizontal, ScatterChart_, ChartPanel}
import org.suit.{Size, DesktopApp}

/**
 * @author Steven Dobay
 */
object ScatterChartWithDispatching
 extends DesktopApp("Sample for scatter chart with dispatching") {

  frame.size = Size(800, 600)

  val scatter = new ScatterChart_("Sample for scatter chart") {
    xAxisTitle := "X"
    yAxisTitle := "Y"
    orientation := Horizontal

    dataSet += ScatterData("A", Array(1, 2), Array(3, 4))
    dataSet += ScatterData("B", Array(3, 1), Array(4, 5))
    dataSet += ScatterData("C", Array(2, 3), Array(4, 5))
    dataSet += ScatterData("D", Array(4, 6), Array(2, 3))
  }

  frame += ChartPanel(scatter)
}
