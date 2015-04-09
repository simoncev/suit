/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * A class for holding values in binding mechanics.
 */
case class HolderOf[T](private var holded: Option[T] = None) {
  def value = holded

  def value_=(v: T) = holded = Some(v)

  def isEmpty = holded.isEmpty

  def makeEmpty() = holded = None
}
