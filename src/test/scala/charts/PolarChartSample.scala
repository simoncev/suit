/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts.ChartPanel

/**
 * @author Steven Dobay
 */
object PolarChartSample extends DesktopApp("Sample for PolarChart") {

  frame.size = Size(800, 600)

  val chart =  charts.PolarChart(
    "Sample for stuff",
    List (
      ("A", Array(Array(1.0, 2.0), Array(3.0, 4.0))),
      ("B", Array(Array(2.0, 3.0), Array(4.0, 5.0)))
    )
  )

  frame += new ChartPanel(chart)

}
