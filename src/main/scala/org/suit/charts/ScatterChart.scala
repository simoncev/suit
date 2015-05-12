/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.xy.DefaultXYDataset

/**
 * @author Steven Dobay
 * @param initTitle
 * @param xAxisTitle
 * @param yAxisTitle
 * @param dataSet
 * @param orientation
 */
case class ScatterChart (
 initTitle: String,
 xAxisTitle: String,
 yAxisTitle: String,
 dataSet: List[ScatterData],
 orientation: Orientation = Vertical) extends Chart {

  private val datasets = new DefaultXYDataset
  for(data <- dataSet) datasets.addSeries(data.label, Array(data.data1, data.data2))

  private val scatter = ChartFactory.createScatterPlot(
    initTitle, xAxisTitle, yAxisTitle, datasets,
    orientation.get(), true, true, false
  )

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = scatter
}

/**
 * Data class for scatter charts.
 *
 * @param label
 * @param data1
 * @param data2
 */
case class ScatterData(label: String, data1: Array[Double],
                       data2: Array[Double])
