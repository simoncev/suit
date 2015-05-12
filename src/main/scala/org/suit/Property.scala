/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
case class Property[T](proc: T => Unit) {
  def :=(v: T) = proc(v)
}
