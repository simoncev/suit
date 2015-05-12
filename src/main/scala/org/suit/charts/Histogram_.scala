/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 */
case class Histogram_(var initTitle: String)
  extends Chart_(initTitle) {

  private var hg = new Histogram("", "", "", List())
  private var xLabel = ""
  private var yLabel = ""
  private var datasets: List[HistogramData] = List()
  private var orient: Orientation = Vertical

  val xAxisTitle = ChartProperty[String](xLabel = _)
  val yAxisTitle = ChartProperty[String](yLabel = _)

  val orientation = ChartProperty[Orientation](orient = _)

  /**
   * Collector of datas.
   */
  object dataSet {
    def add(data: HistogramData) =
      datasets = datasets ++ List(data)

    def += = add _
  }

  /**
   * Initializes the chart.
   */
  protected[suit] def onPack() =
    hg = new Histogram(
      initTitle, xLabel, yLabel, datasets, orient
    )

  protected[suit] def chart() = hg
}
