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

  /**
   * @return with the chart-object
   */
  def chart = chartComponent

  /**
   * Sets the chart-panel's chart-object
   * @param c
   */
  def chart_=(c: Chart) = {
    chartComponent = c
    panel.setChart(c.jFreeChart)
  }

  /**
   * Saves the chart as JPG
   * @param path
   */
  def saveAsJpg(path: String): Unit = {
    val size = panel.getPreferredSize
    ChartUtilities.saveChartAsJPEG(
      new java.io.File(path), chartComponent.jFreeChart,
      size.getWidth.toInt, size.getHeight.toInt
    )
  }

  /**
   * Saves the chart as PNG
   * @param path
   */
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

object ChartPanel {
  /**
   * Creates a chart panel from a chart's dispatcher
   * @param chart a Chart_
   * @return with the new chart panel
   */
  def apply(chart: Chart_) = new ChartPanel(chart.pack())
}
