/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 */
case class AreaChart_[T: Numeric](initTitle: String)
 extends Chart_(initTitle) {

  private var categoryLabel = ""
  private var valueLabel = ""
  private var orient: Orientation = Vertical
  private var datasets: List[(T, String, String)] = List()
  private var ch = new AreaChart[T]("", "", "", List())

  val categoryTitle = ChartProperty[String](categoryLabel = _)
  val valueTitle = ChartProperty[String](valueLabel = _)
  val orientation = ChartProperty[Orientation](orient = _)

  object dataSet {
    def add(set: (T, String, String)) =
     datasets = datasets ++ List((set))

    def +=(value: T, row: String, column: String) =
      add((value, row, column))
  }

  protected[suit] def onPack() =
     ch = new AreaChart(
       initTitle, categoryLabel, valueLabel, datasets, orient
     )

  protected[suit] def chart() = ch
}
