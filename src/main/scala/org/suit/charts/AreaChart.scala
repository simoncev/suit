/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.data.category.DefaultCategoryDataset

/**
 * @author Steven Dobay
 *
 * @param initTitle
 * @param categoryTitle
 * @param valueTitle
 * @param orientation
 */
case class AreaChart[T: Numeric](
 initTitle: String,
 categoryTitle: String,
 valueTitle: String,
 dataSet: List[(T, String, String)],
 orientation: Orientation = Vertical
) extends Chart {

  private val datasets = new DefaultCategoryDataset
  for(set <- dataSet)
    datasets.addValue(set._1.asInstanceOf[Number], set._2, set._3)

  private val chart = ChartFactory.createAreaChart(
   initTitle, categoryTitle, valueTitle,
   datasets, orientation.get(), true, true, false
  )

  protected[suit] def jFreeChart = chart
}
