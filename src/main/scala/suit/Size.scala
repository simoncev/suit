/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
case class Size(private val dimWidth: Int, private val dimHeight: Int)
  extends java.awt.Dimension(dimWidth, dimHeight) {

  def width() = dimWidth
  def height() = dimHeight
}

object Size {
 def apply() = new Size(0, 0)
 def apply(t: (Int, Int)) = new Size(t._1, t._2)

 def apply(d: java.awt.Dimension): Size =
  Size(d.getWidth.toInt, d.getHeight.toInt)
}
