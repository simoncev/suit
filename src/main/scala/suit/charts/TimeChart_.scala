/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import suit._
import suit.charts._

/**
 * @author Steven Dobay
 *
 * Dispatcher for TimeChart.
 */
case class TimeChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var timeChart: TimeChart[T] = null

  private var xtitle = ""
  private var ytitle = ""
  private var dataset: List[TimeSeries[T]] = List()

  val xTitle = Property[String](xtitle = _)
  val yTitle = Property[String](ytitle = _)
  val withSeries = Property[TimeSeries[T]](series =>
   dataset = dataset ++ List(series)
  )

  protected[suit] def chart() = timeChart

  protected[suit] def onPack() =
   timeChart = new TimeChart[T](initTitle, xtitle, ytitle)
                    .withSeries(dataset)
   
}
