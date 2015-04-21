/**
 * Copyright© Steven Dobay 2015
 */
package charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */

object PieChartSample extends DesktopApp("Pie Chart Sample") {

  frame.size = Size(800, 600)

  val pie = PieChart (
   "Candy market",
    List(("Chocolates", 10),
         ("Biscuits", 7),
         ("Cakes", 5))
  )

  frame += new ChartPanel(pie)
}
