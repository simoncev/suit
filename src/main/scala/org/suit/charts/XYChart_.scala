/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.suit.Property

/**
 * Dispatcher for XYChart.
 *
 * @author Steven Dobay
 * @param initTitle
 * @param ev1
 * @tparam T
 */
case class XYChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var xychart: XYChart[T] = null

  private var xtitle: String = ""
  private var ytitle: String = ""
  private var orient: Orientation = Vertical
  private var dataset: List[XYSeries[T]] = List()

  val xTitle = Property[String](xtitle = _)
  val yTitle = Property[String](ytitle = _)
  val orientation = Property[Orientation](orient = _)

  /**
   * Collector of datas.
   */
  object dataSet {
    /**
     * @param series
     */
    def add(series: XYSeries[T]) =
      dataset = dataset ++ List(series)

    def += = add _
  }

  /**
   * @return with the chart.
   */
  protected[suit] def chart() = xychart

  protected[suit] def onPack() = {
    xychart = new XYChart[T](initTitle, xtitle, ytitle, orient)
    for(series <- dataset) xychart.withSeries(series)
  }
}
