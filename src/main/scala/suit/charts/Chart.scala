/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import java.awt.Image
import org.jfree.chart.JFreeChart

/**
 * @author Steven Dobay
 * Abstraction for all charts. Only ChartPanel excepts
 * charts!
 */
trait Chart {

  /**
   * @return with the wrapped JFree Chart
   */
  protected[suit] def jFreeChart: JFreeChart

  /**
   * @return with the title object of the chart
   */
  def title = TextTitle(jFreeChart.getTitle)

  /**
   * Sets the chart's title by a TextTitle
   * @param t
   */
  def title_=(t: TextTitle) = jFreeChart.setTitle(t.jFreeTextTitle)

  /**
   * @return with the background image
   */
  def backgroundImage = jFreeChart.getBackgroundImage

  /**
   * Sets the chart's background image
   * @param i
   */
  def backgroundImage_=(i: Image) = jFreeChart.setBackgroundImage(i)

  /**
   * With this the chart's antialiasing can be switched on and off.
   * @param flag
   */
  def antiAliasing(flag: Boolean) = jFreeChart.setAntiAlias(flag)

  /**
   * @return true if antialiasing is enabled
   */
  def isAntialiasingEnabled() = jFreeChart.getAntiAlias

  /**
   * @return true if the chart's border is visible
   */
  def isBorderVisible() = jFreeChart.isBorderVisible

  /**
   * Sets the border's visibility
   * @param flag
   */
  def borderVisible(flag: Boolean) = jFreeChart.setBorderVisible(flag)
}
