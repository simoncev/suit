/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import suit._

/**
 * @author Steven Dobay
 *
 * Creates a simple bar-chart.
 *
 * @param initTitle   : title
 * @param xTitle      : title of the x-axis
 * @param yTitle      : title of the y-axis
 * @param dataSet     : the list of the data set in Tuple3s
 * @param orientation : the plot's orientation
 * @param dimension   : the dimension of the bar chart
 * @param ev1         : the chart's values must be Numerics
 * @tparam T
 */
case class BarChart[T: Numeric](
 initTitle: String,
 xTitle: String,
 yTitle: String,
 dataSet: List[(T, String, String)],
 orientation: Orientation = Vertical,
 dimension: Dimension = _2D
) extends Chart {

  private val dataset = new DefaultCategoryDataset
  for(v <- dataSet)
    dataset.addValue(v._1.asInstanceOf[Number], v._2, v._3)

  private val bar =
    if(dimension == _2D)
     ChartFactory.createBarChart(
      initTitle, xTitle, yTitle, dataset,
      plotOrientation, true, true, false
     )
    else
     ChartFactory.createBarChart3D(
      initTitle, xTitle, yTitle, dataset,
      plotOrientation, true, true, false
     )

  private def plotOrientation() =
    if(orientation == Horizontal) PlotOrientation.HORIZONTAL
    else PlotOrientation.VERTICAL

  protected[suit] def jFreeChart = bar
}