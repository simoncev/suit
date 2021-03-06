/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 *
 * 3D bar chart created with dispatching.
 */
object BarChart3DWithDispatcher extends DesktopApp("Bar chart with Dispatcher 3D") {

  frame.size = Size(800, 600)

  val chart = new BarChart_[Int]("Charts!") {
    xTitle := "X"
    yTitle := "Y"
    orientation := Horizontal
    dimension   := _3D

    dataSet += (10, "First", "One")
    dataSet += (7, "First", "Two")
    dataSet += (8, "Second", "One")
    dataSet += (6, "Second", "Two")
  }

  frame += ChartPanel(chart)
}
