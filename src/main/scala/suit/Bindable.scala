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

  protected def onChangeDoBind(variable: HolderOf[T]): Unit

  def holder = holderV
  def holder_=(h: HolderOf[T]) = {
    holderV = h
    onChangeDoBind(h)
  }
}
