/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit.layouts

import suit._

/**
 * @author Steven Dobay
 */
case class GridLayout(var rows: Int, var columns: Int) extends Layout {
  private val layout = new java.awt.GridLayout(rows, columns)

  /**
   * @return with the size of the layout
   */
  def size = Dim(rows, columns)

  /**
   * Sets the size of the layout
   * @param dim
   */
  def size_=(dim: Dim) = {
    rows = dim.width()
    columns = dim.height()
    layout.setRows(rows)
    layout.setColumns(columns)
  }

  /**
   * @return with the size of the horizontal gap
   */
  def hGap = layout.getHgap

  /**
   * @return with the size of the vertical gap
   */
  def vGap = layout.getVgap

  /**
   * Sets the horizontal gap of the layout
   * @param h
   */
  def hGap_=(h: Int) = layout.setHgap(h)

  /**
   * Sets the vertical gap of the layout
   * @param v
   */
  def vGap_=(v: Int) = layout.setVgap(v)

  /**
   * @return with a pointer to the wrapped layout manager
   */
  protected[suit] def wrapped = layout
}

object GridLayout {

  def apply(dim: Dim): GridLayout =
    GridLayout(dim.width(), dim. height())
}