/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import charts.{ChartPanel, RingChart}

/**
 * @author Steven Dobay
 */
object RingChartSample extends DesktopApp("Sample for ring chart") {
  frame.size = Size(800, 600)

  val pie = RingChart (
    "Candy market",
    List(("Chocolates", 10),
         ("Biscuits", 7),
         ("Cakes", 5))
  )

  frame += new ChartPanel(pie)
}
