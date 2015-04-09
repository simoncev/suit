/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class ProgressBar_(private val lowerBound: Int = 0,
                            private val upperBound: Int = 100)
  extends Widget_ {

  private val bar = new ProgressBar(lowerBound, upperBound)

  val min = Property[Int](bar.min = _)
  val max = Property[Int](bar.max = _)
  val value = Property[Int](bar.value = _)

  def pack() = bar
}
