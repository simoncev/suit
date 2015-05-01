/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.suit._

/**
 * Dispatcher for BarChart.
 * @author Steven Dobay
 * @param initTitle
 * @param ev1
 * @tparam T
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

  /**
   * Collector of datas.
   */
  object dataSet {
    def add(value: T, column: String, row: String) =
      dataset = dataset ++ List((value, column, row))

    def += = add _
  }

  protected[suit] def chart() = bar

  protected[suit] def onPack() =
   bar = new BarChart[T](
    initTitle, xtitle, ytitle, dataset, orient, dim
   )
}