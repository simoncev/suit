/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 *
 * Binds a component's contained value into the given Holder instance
 */
trait Bindable[T] extends Stateful[T] {
  protected var holderV = HolderOf[T]()

  def componentValue(): T

  def holder = holderV
  def holder_=(h: HolderOf[T]) = {
    holderV = h
    changeEvents += (_ => h.value = componentValue)
  }
}
