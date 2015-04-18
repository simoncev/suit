/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.{JComponent, JToolBar}

/**
 * @author Steven Dobay
 */
case class Toolbar() extends ContainerComponent {
  private val toolbar = new JToolBar()

  toolbar.putClientProperty ("suit-wrapper", this)

  def addSeparator = toolbar.add(new JToolBar.Separator())

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() =
    toolbar.getComponents
           .map(_.asInstanceOf[JComponent]
                 .getClientProperty("suit-wrapper")
                 .asInstanceOf[Component])

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    toolbar.getComponentCount

  protected[suit] def wrapped = toolbar

  def className = "ToolBar"

  protected[suit] def wrappedContainer = toolbar
}
