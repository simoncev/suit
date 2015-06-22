/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import scala.reflect.ClassTag

/**
 * @author Steven Dobay
 * For the properties' meaning go to ListView
 */
abstract class ListView_[T <: AnyRef](private val items: T*)
                                     (implicit CT: ClassTag[T]) extends Component_ {

  private val view = new ListView(items.toVector)

  def pack() = view

  def addScrollPane = view.addScrollPane()

  val visibleRowCount = Property[Int](view.visibleRowCount = _)

  val selectionMode = Property[Int](view.selectionMode = _)

  val layoutOrientation = Property[Int](view.layoutOrientation = _)

  val fixedSize = Property[Size](view.fixedSize(_))

  val onSelection =
    Property[(ListView[T], Int, Int) => Unit](view.onSelection(_))
}
