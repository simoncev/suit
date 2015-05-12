/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 * @param initTitle
 */
case class PolarChart_(initTitle: String = "")
  extends Chart_(initTitle) {

  private var datasets: List[(String, Array[Array[Double]])] = List()
  private var polarChart: Chart = new PolarChart("", List())

  object dataSet {
    def add(seriesTitle: String,
            data: Array[Array[Double]]) =
      datasets = datasets ++ List((seriesTitle, data))

    def += = add _
  }

  protected[suit] def chart() = polarChart

  protected[suit] def onPack() =
    polarChart = new PolarChart(initTitle, datasets)

}
