/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 */
case class ScatterChart_(var initTitle: String) extends Chart_(initTitle) {
  private var scatter = new ScatterChart("", "", "", List())
  private var orient: Orientation = Vertical
  private var xLabel = ""
  private var yLabel = ""
  private var datasets: List[ScatterData] = List()

  val orientation = ChartProperty[Orientation](orient = _)
  val xAxisTitle = ChartProperty[String](xLabel = _)
  val yAxisTitle = ChartProperty[String](yLabel = _)

  /**
   * Collector of datas.
   */
  object dataSet {
    def add(set: ScatterData) =
      datasets = datasets ++ List(set)

    def += = add _
  }

  /**
   * @return with the chart.
   */
  protected[suit] def chart() = scatter

  /**
   * Inititlaizes the chart's plot.
   */
  protected[suit] def onPack() =
   scatter = new ScatterChart(
     initTitle, xLabel, yLabel, datasets, orient
   )
}
