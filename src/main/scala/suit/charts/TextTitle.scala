/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import java.awt.{Font, Paint}
import org.jfree.chart.title.{TextTitle => JFreeTextTitle}

/**
 * @author Steven Dobay
 *
 * Data class for charts' title.
 *
 * @param txt : the initial title
 */
case class TextTitle(private val txt: String = "") {
  private var textTitle = new JFreeTextTitle(txt)

  /**
   * @return with the text of the title
   */
  def text = textTitle.getText

  /**
   * Sets the title
   * @param t
   */
  def text_=(t: String) = textTitle.setText(t)

  /**
   * @return with the background paint of this.
   */
  def backgroundPaint = textTitle.getBackgroundPaint

  /**
   * Sets the background's paint
   * @param p
   */
  def backgroundPaint_=(p: Paint) = textTitle.setBackgroundPaint(p)

  /**
   * @return with the font-style of the data-class
   */
  def font = textTitle.getFont

  /**
   * Sets the font-style
   * @param f
   */
  def font_=(f: Font) = textTitle.setFont(f)

  /**
   * @return with the paint object
   */
  def paint = textTitle.getPaint

  /**
   * Sets paint object.
   * @param p
   */
  def paint_=(p: Paint) = textTitle.setPaint(p)

  /**
   * @return with the tooltip-text.
   */
  def toolTip = textTitle.getToolTipText

  /**
   * Sets the tooltip-text.
   * @param t
   */
  def toolTip_=(t: String) = textTitle.setToolTipText(t)

  /**
   * @return true if the title is expanded to the fitting space
   */
  def isExpandToFitSpace() = textTitle.getExpandToFitSpace

  /**
   * Sets the text to expand it to the fit-space
   * @param b
   */
  def expandToFitSpace(b: Boolean) =
    textTitle.setExpandToFitSpace(b)

  /**
   * @return with the url-text
   */
  def urlText = textTitle.getURLText

  /**
   * Sets the url-text
   * @param t
   */
  def urlText_=(t: String) = textTitle.setURLText(t)

  /**
   * @param jt
   */
  protected[suit] def jFreeTextTitle(jt: JFreeTextTitle) =
    textTitle = jt

  /**
   * @return with JFreechart's wrapped text-title
   */
  protected[suit] def jFreeTextTitle = textTitle
}

object TextTitle {

  /**
   * Creates a TextTitle from a org.jfree.chart.title.TextTitle
   * @param jFreeTextTitle
   */
  def apply(jFreeTextTitle: JFreeTextTitle) = {
    val t = new TextTitle()
    t.jFreeTextTitle(jFreeTextTitle)
    t
  }
}
