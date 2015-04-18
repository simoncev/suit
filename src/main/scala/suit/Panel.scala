/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.{JComponent, JPanel}

/**
 * @author Steven Dobay
 */
case class Panel() extends ContainerComponent {

  private val panel = new JPanel

  panel.putClientProperty ("suit-wrapper", this)

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() =
    panel.getComponents
         .map(_.asInstanceOf[JComponent]
               .getClientProperty("suit-wrapper")
               .asInstanceOf[Component])

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    panel.getComponentCount

  protected[suit] def wrapped = panel
  def className = "Panel"

  protected[suit] def wrappedContainer = panel
}
