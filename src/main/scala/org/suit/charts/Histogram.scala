/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.xy.DefaultIntervalXYDataset

/**
 * @author Steven Dobay
 * @param initTitle
 * @param xAxisTitle
 * @param yAxisTitle
 * @param dataSet
 * @param orientation
 */
case class Histogram (
 initTitle: String,
 xAxisTitle: String,
 yAxisTitle: String,
 dataSet: List[HistogramData],
 orientation: Orientation = Vertical
) extends Chart {

  private val datasets = new DefaultIntervalXYDataset
  for(data <- dataSet)
    datasets.addSeries(data.label, data.data)

  private val hg = ChartFactory.createHistogram(
    initTitle, xAxisTitle, yAxisTitle, datasets,
    orientation.get(), true, true, false
  )

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = hg
}

/**
 * Data class for a histogram's datas.
 * @param label
 * @param data : ots length must be 6!
 */
case class HistogramData(label: String, data: Array[Array[Double]]) {
  require(data.size == 6)
}