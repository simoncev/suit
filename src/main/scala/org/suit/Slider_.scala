/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

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

  val onChange = Property[ChangeEvent => Unit](slider.changeEvents += _)

  val holder = Property[HolderOf[Int]](slider.holder = _)

  val minorSpaciong = Property[Int](pack.minorSpacing = _)
  val majorSpaciong = Property[Int](pack.majorSpacing = _)

  val paintTicks = Property[Boolean](if(_) pack.paintTicks()
                                     else pack.hideTicks())

  def pack() = slider
}
