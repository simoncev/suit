/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

/**
 * @author Steven Dobay
 */
case class ChartProperty[T](private val proc: T => Unit) {
  private var value: Option[T] = None

  def :=(v: T) = value = Some(v)
  def isDefined() = value.isDefined
  protected[suit] def set() = if(isDefined) proc(value.get)
}