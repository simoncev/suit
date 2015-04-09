/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class Slider_(private val initMin: Int = 0,
                       private val initMax: Int = 100)
   extends Widget_ {
  private val slider = new Slider(initMin, initMax)

  val min = Property[Int](slider.min = _)
  val max = Property[Int](slider.max = _)
  val value = Property[Int](slider.value = _)
}
