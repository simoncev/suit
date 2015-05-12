/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.xy.DefaultXYDataset

/**
 * @author Steven Dobay
 *
 * @param initTitle
 * @param dataSet   : a list of tuples with titles and double-tables
 */
case class PolarChart(
  initTitle: String,
  dataSet: List[(String, Array[Array[Double]])]
) extends Chart {

  private val dataset = new DefaultXYDataset()

  for(set <- dataSet) dataset.addSeries(set._1, set._2)

  private val chart = ChartFactory.createPolarChart(
    initTitle, dataset, true, true, false
  )

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = chart
}
