/**
 * Copyright© Steven Dobay 2015
 */
package charts

import suit._
import suit.charts._

/**
 * @author Steven Dobay
 */

object PieChartSample extends App("Pie Chart Sample") {

  frame.size = Dim(800, 600)

  val pie = PieChart (
   "Candy market",
    List(("Chocolates", 10),
         ("Biscuits", 7),
         ("Cakes", 5))
  )

  frame += new ChartPanel(pie)
}
