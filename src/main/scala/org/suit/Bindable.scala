/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 *
 * Binds a component's contained value into the given Holder instance
 */
trait Bindable[T] extends Stateful[T] {
  protected var holderV = HolderOf[T]()

  /**
   * @return with the component's value
   */
  def componentValue(): T

  /**
   * Binds the value to the component.
   * @param value
   */
  protected def setValue(value: T): Unit

  /**
   * Change the component's value by a function.
   * @param fn
   */
  def map(fn: T => T) = setValue(fn(componentValue()))

  /**
   * @return with the value holder.
   */
  def holder = holderV

  /**
   * Sets the value holder.
   * @param h
   */
  def holder_=(h: HolderOf[T]) = {
    holderV = h
    changeEvents += (_ => h.value = componentValue)
  }

}
