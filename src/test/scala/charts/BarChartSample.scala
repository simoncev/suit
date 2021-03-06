/**
 * Copyright© Steven Dobay 2015
 */
package charts

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object BarChartSample extends DesktopApp("Vertical bar chart Sample") {
  frame.size = Size(800, 600)

  val chart = BarChart (
    "My Bar",                    //title
    "X", "Y",                    // x- and y axis-titles
    List((10, "First", "One"),  //dataset...
         (7, "First", "Two"),
         (8, "Second", "One"),
         (6, "Second", "Two"))
    // You can set the orientation here: Vertical or Horizontal
    // or the dimension: _2D or _3D
  )

  frame += new ChartPanel(chart)
}
