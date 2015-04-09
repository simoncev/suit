/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Dimension

/**
 * @author Steven Dobay
 */
case class Dim(private val dimWidth: Int, private val dimHeight: Int)
  extends Dimension(dimWidth, dimHeight)

object Dim {
 def apply() = new Dim(0, 0)
 def apply(t: (Int, Int)) = new Dim(t._1, t._2)

 def apply(d: Dimension): Dim =
  Dim(d.getWidth.toInt, d.getHeight.toInt)
}
