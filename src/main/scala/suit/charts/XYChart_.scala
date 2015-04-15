/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import suit.Property

/**
 * @author Steven Dobay
 *
 * Dispatcher for XYChart.
 */
case class XYChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var xychart: XYChart[T] = null

  private var xtitle: String = ""
  private var ytitle: String = ""
  private var orient: Orientation = Vertical
  private var dataset: List[Series[T]] = List()

  val xTitle = Property[String](xtitle = _)
  val yTitle = Property[String](ytitle = _)
  val orientation = Property[Orientation](orient = _)
  val withSeries = Property[Series[T]](series =>
    dataset = dataset ++ List(series)
  )

  protected[suit] def chart() = xychart

  protected[suit] def onPack() = {
    xychart = new XYChart[T](initTitle, xtitle, ytitle, orient)
    for(series <- dataset) xychart.withSeries(series)
  }
}
