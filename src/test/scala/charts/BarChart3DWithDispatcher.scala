/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import suit._
import suit.charts._

/**
 * @author Steven Dobay
 *
 * 3D bar chart created with dispatching.
 */
object BarChart3DWithDispatcher extends App("Bar chart with Dispatcher 3D") {

  frame.size = Dim(800, 600)

  val chart = new BarChart_[Int]("Charts!") {
    xTitle := "X"
    yTitle := "Y"
    dataSet := List (
      (10, "First", "One"),  //dataset...
      (7, "First", "Two"),
      (8, "Second", "One"),
      (6, "Second", "Two")
    )
    orientation := Horizontal
    dimension   := _3D
  }

  frame += new ChartPanel(chart.pack())
}
