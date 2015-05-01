/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.suit._
import org.suit.charts._

/**
 * Dispatcher for TimeChart.
 *
 * @author Steven Dobay
 * @param initTitle
 * @param ev1
 * @tparam T
 */
case class TimeChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var timeChart: TimeChart[T] = null

  private var xtitle = ""
  private var ytitle = ""
  private var dataset: List[TimeSeries[T]] = List()

  val xTitle = Property[String](xtitle = _)
  val yTitle = Property[String](ytitle = _)

 /**
  * Collector of datas.
  */
  object dataSet {
    def add(series: TimeSeries[T]) =
      dataset = dataset ++ List(series)

    def += = add _
  }

 /**
  * @return with the chart.
  */
  protected[suit] def chart() = timeChart

  protected[suit] def onPack() =
   timeChart = new TimeChart[T](initTitle, xtitle, ytitle)
                    .withSeries(dataset)
   
}
