/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import suit.Property

/**
 * @author Steven Dobay
 */
case class PieChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var pie: PieChart[T] = null

  private var dataset: List[(String, T)] = List()
  private var dim: ChartDimension = _2D

  val dataSet = Property[List[(String, T)]](dataset = _)
  val dimension = Property[ChartDimension](dim = _)

  protected[suit] def chart() = pie

  protected[suit] def onPack() =
    pie = new PieChart[T](initTitle, dataset, dim)
}