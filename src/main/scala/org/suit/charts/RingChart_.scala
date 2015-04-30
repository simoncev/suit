/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 * @param ev1
 * @tparam T
 */
case class RingChart_[T: Numeric](var initTitle: String)
  extends Chart_(initTitle) {

  private var datasets: List[(String, T)] = List()
  private var ring = new RingChart[T]("", List())

  /**
   * Collector of datas.
   */
  object dataSet {
    def add(label: String, value: T) =
     datasets = datasets ++ List((label, value))

    def +=(label: String, value: T) = add(label, value)
  }

  /**
    * Initialized the chart.
   */
  protected[suit] def onPack() =
   ring = new RingChart[T](initTitle, datasets)

  /**
   * @return with the chart.
   */
  protected[suit] def chart() = ring
}
