/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import suit._

/**
 * @author Steven Dobay
 *
 * Dispatcher for BarChart.
 */
abstract class BarChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var bar: BarChart[T] = null
  private var xtitle = ""
  private var ytitle = ""
  private var dataset: List[(T, String, String)] = List()
  private var orient: Orientation = Vertical
  private var dim: Dimension = _2D

  val xTitle = Property[String](xtitle = _)

  val yTitle = Property[String](ytitle = _)

  val orientation = Property[Orientation](orient = _)

  val dimension = Property[Dimension](dim = _)

  val dataSet =
   Property[List[(T, String, String)]](dataset = _)

  protected[suit] def chart() = bar

  protected[suit] def onPack() =
   bar = new BarChart[T](
    initTitle, xtitle, ytitle, dataset, orient, dim
   )
}