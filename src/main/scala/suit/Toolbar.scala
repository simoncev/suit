/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JToolBar

/**
 * @author Steven Dobay
 */
case class Toolbar() extends ContainerComponent {
  private val toolbar = new JToolBar()

  toolbar.putClientProperty ("scala-frame-wrapper", this)

  def addSeparator = toolbar.add(new JToolBar.Separator())

  protected[suit] def wrapped = toolbar

  def className = "ToolBar"
}
