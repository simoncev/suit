/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.xy.DefaultXYZDataset

/**
 * @author Steven Dobay
 * @param initTitle
 * @param xAxisTitle
 * @param yAxisTitle
 * @param dataSet
 * @param orientation : default is Vertical
 */
case class BubbleChart(
 initTitle: String,
 xAxisTitle: String,
 yAxisTitle: String,
 dataSet: List[BubbleData],
 orientation: Orientation = Vertical
) extends Chart {

  private val datasets = new DefaultXYZDataset
  for(set <- dataSet)
    datasets.addSeries(set.label, set.data)

  private val chart = ChartFactory.createBubbleChart(
   initTitle, xAxisTitle, yAxisTitle, datasets, orientation.get(),
   true, true, false
  )

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = chart
}

/**
 * Data class for bubble-chart's datas.
 * @param label
 * @param data : the length must be 3!
 */
case class BubbleData(label: String, data: Array[Array[Double]]) {
  require(data.size == 3)
}
