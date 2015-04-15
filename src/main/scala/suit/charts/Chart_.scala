/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import java.awt.Image

/**
 * @author Steven Dobay
 */
abstract class Chart_(private val initTitle: String) {
  protected var properties: List[ChartProperty[_]] = List()

  protected[suit] def chart(): Chart
  protected[suit] def onPack(): Unit
  
  val title = ChartProperty[TextTitle](chart.title = _)

  val backgroundImage = ChartProperty[Image](chart.backgroundImage = _)

  val borderVisible = ChartProperty[Boolean](chart.borderVisible(_))

  val antiAliasing = ChartProperty[Boolean](chart.antiAliasing(_))

  properties = List(title, backgroundImage, borderVisible, antiAliasing)

  def pack() = {
    onPack()
    properties.foreach(_.set())
    chart()
  }
}
