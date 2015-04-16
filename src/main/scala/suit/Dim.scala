/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
case class Dim(private val dimWidth: Int, private val dimHeight: Int)
  extends java.awt.Dimension(dimWidth, dimHeight) {

  def width() = dimWidth
  def height() = dimHeight
}

object Dim {
 def apply() = new Dim(0, 0)
 def apply(t: (Int, Int)) = new Dim(t._1, t._2)

 def apply(d: java.awt.Dimension): Dim =
  Dim(d.getWidth.toInt, d.getHeight.toInt)
}
