/**
 * Copyright© Steven Dobay 2015
 */
package charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object BarChart3DSample extends DesktopApp("Horizontal bar chart in 3D Sample") {
  frame.size = Size(800, 600)

  val chart = BarChart (
    "My Bar",                    //title
    "X", "Y",                    // x- and y axis-titles
    List((10, "First", "One"),  //dataset...
      (7, "First", "Two"),
      (8, "Second", "One"),
      (6, "Second", "Two")),
    Horizontal, _3D
  )

  frame += new ChartPanel(chart)
}
