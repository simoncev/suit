/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import org.jfree.chart.{ChartUtilities, ChartPanel => JChartPanel}
import suit.Component

/**
 * @author Steven Dobay
 */
case class ChartPanel(private var chartComponent: Chart)
 extends Component {

  private val panel = new JChartPanel(chartComponent.jFreeChart)

  def chart = chartComponent

  def chart_=(c: Chart) = {
    chartComponent = c
    panel.setChart(c.jFreeChart)
  }

  def saveAsJpg(path: String): Unit = {
    val size = panel.getPreferredSize
    ChartUtilities.saveChartAsJPEG(
      new java.io.File(path), chartComponent.jFreeChart,
      size.getWidth.toInt, size.getHeight.toInt
    )
  }

  def saveAsPng(path: String): Unit = {
    val size = panel.getPreferredSize
    ChartUtilities.saveChartAsPNG(
      new java.io.File(path), chartComponent.jFreeChart,
      size.getWidth.toInt, size.getHeight.toInt
    )
  }

  protected[suit] def wrapped = panel

  def className = "ChartPanel"
}
