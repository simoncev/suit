/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.general.DefaultPieDataset

/**
 * @author Steven Dobay
 * @param initTitle
 * @param dataSet
 * @param ev1
 * @tparam T
 */
case class RingChart[T: Numeric] (
 initTitle: String,
 dataSet: List[(String, T)]
) extends Chart {

  private val datasets = new DefaultPieDataset()
  for(data <- dataSet)
    datasets.setValue(data._1, data._2.asInstanceOf[Number])

  private val chart = ChartFactory.createRingChart(
   initTitle, datasets, true, true, false
  )

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart = chart
}
