/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}

/**
 * @author Steven Dobay
 *
 * Simple class for creating basic xy charts.
 *
 * @param initTitle   : title
 * @param xTitle      : title of th x axis
 * @param yTitle      : title of the y axis
 * @param orientation : orientation of the plot
 * @param ev1         the values must be Numerics
 * @tparam T
 */
case class XYChart[T: Numeric](
  initTitle: String,
  xTitle: String,
  yTitle: String,
  orientation: Orientation = Vertical
) extends Chart {

  private val collection = new XYSeriesCollection()
  private var chart = create()

  /**
   * Adds a new series to the chart
   * @param xTitle
   * @param values
   */
  def withSeries(xTitle: String, values: List[(T, T)]) = {
    val series = new XYSeries(xTitle)
    for(v <- values) series.add(v._1.asInstanceOf[Number],
                                v._2.asInstanceOf[Number])
    collection.addSeries(series)
    this
  }

  /**
   * Same as method withSeries
   */
  def ++ = withSeries _

  /**
   * @return with the fully initialized chart.
   */
  def createChart() = {
    chart = create()
    this
  }

  private def create() =
    ChartFactory.createXYLineChart(
      initTitle, xTitle, yTitle, collection,
      plotOrientation, true, true, false
    )

  private def plotOrientation() =
   if(orientation == Vertical) PlotOrientation.VERTICAL
   else PlotOrientation.HORIZONTAL

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = chart
}