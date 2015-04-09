/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
case class Property[T](proc: T => Unit) {
  def :=(v: T) = proc(v)
}
