/**
 * Copyright© Steven Dobay 2015
 */
package suit.helpers

/**
 * @author Steven Dobay
 *
 * Helpers for array operations.
 */
protected[suit] object ArrayHelpers {

  /**
   * Created to remove an elem by index.
   * @param a
   */
  final implicit class ArrayIndexRemover[T](a: Array[T]) {
    def removeOnIndex(ix: Int) = a.take(ix) ++ a.drop(ix + 1)
  }

}
