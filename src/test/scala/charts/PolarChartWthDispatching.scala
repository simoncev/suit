/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts.ChartPanel

/**
 * @author Steven Dobay
 */
object PolarChartWithDispatching
  extends DesktopApp("Sample for PolarChart with dispatching") {

  frame.size = Size(800, 600)

  val chart = new charts.PolarChart_("Sample for stuff") {
    dataSet += ("A", Array(Array(1.0, 2.0), Array(3.0, 4.0)))
    dataSet += ("B", Array(Array(2.0, 3.0), Array(4.0, 5.0)))
  }

  frame += ChartPanel(chart)

}
