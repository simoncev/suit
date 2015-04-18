/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
trait Container {
  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer: java.awt.Component

  /**
   * @return with all components.
   */
  protected[suit] def allComponents(): Array[Component]

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize(): Int

  /**
   * @return with an array of components
   */
  def components() =
    allComponents()
     .map(_.asInstanceOf[JComponent]
           .getClientProperty("suit-wrapper")
           .asInstanceOf[Component])

  /**
   * @return with the number of components
   */
  def componentsCount() = componentsSize()
}
