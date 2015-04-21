/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object PieChart3DWithDispatching extends DesktopApp("3D pie chart with dispatching!") {

  frame.size = Size(800, 600)

  val chart = new PieChart_[Int]("Candies!") {
    dataSet := List(
      ("Chocolates", 10),
      ("Biscuits", 7),
      ("Cakes", 5)
    )
    dimension := _3D
  }

  frame += ChartPanel(chart)
}
