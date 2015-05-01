/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import org.suit._

/**
 * @author Steven Dobay
 * @param initTitle
 * @param ev1
 * @tparam T
 */
case class PieChart_[T: Numeric](private val initTitle: String = "")
 extends Chart_(initTitle) {

  private var pie: PieChart[T] = null

  private var dataset: List[(String, T)] = List()
  private var dim: Dimension = _2D

  val dimension = Property[Dimension](dim = _)

  /**
   * Collector of datas.
   */
  object dataSet {
    def add(label: String, value: T) =
      dataset = dataset ++ List((label, value))

    def += = add _
  }

  protected[suit] def chart() = pie

  protected[suit] def onPack() =
    pie = new PieChart[T](initTitle, dataset, dim)
}