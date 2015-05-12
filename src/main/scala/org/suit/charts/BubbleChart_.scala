/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 */
case class BubbleChart_(var initTitle: String)
 extends Chart_(initTitle) {
  private var bubbleChart = BubbleChart("", "", "", List())
  private var xTitle = ""
  private var yTitle = ""
  private var orient: Orientation = Vertical
  private var datasets: List[BubbleData] = List()

  val xAxisTitle = ChartProperty[String](xTitle = _)
  val yAxisTitle = ChartProperty[String](yTitle = _)

  val orientation = ChartProperty[Orientation](orient = _)

  object dataSet {
    def add(bubbleData: BubbleData) =
     datasets = datasets ++ List(bubbleData)

    def +=(bubbleData: BubbleData) =
      datasets = datasets ++ List(bubbleData)
  }

  protected[suit] def chart() = bubbleChart
  protected[suit] def onPack() =
   bubbleChart = new BubbleChart(
     initTitle, xTitle, yTitle, datasets, orient
   )
}
