/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.Graphics
import javax.swing.JToolBar

/**
 * @author Steven Dobay
 */
case class Toolbar() extends ContainerComponent {
  private val toolbar = new JToolBar() {
    override def paintComponent(g: Graphics) = {
      super.paintComponent(g)
      paintObjects(g)
    }
  }

  toolbar.putClientProperty ("suit-wrapper", this)

  def addSeparator = toolbar.add(new JToolBar.Separator())

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() = toolbar.getComponents

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    toolbar.getComponentCount

  protected[suit] def wrapped = toolbar

  def className = "ToolBar"

  protected[suit] def wrappedContainer = toolbar
}
