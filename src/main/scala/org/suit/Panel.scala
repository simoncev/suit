/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.Graphics
import javax.swing.JPanel

/**
 * @author Steven Dobay
 */
case class Panel() extends ContainerComponent {

  private val panel = new JPanel {
    override def paintComponent(g: Graphics) = {
      super.paintComponent(g)
      paintObjects(g)
    }
  }

  panel.putClientProperty ("suit-wrapper", this)

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() = panel.getComponents

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    panel.getComponentCount

  protected[suit] def wrapped = panel
  def className = "Panel"

  protected[suit] def wrappedContainer = panel
}
