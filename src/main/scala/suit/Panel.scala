/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JPanel

/**
 * @author Steven Dobay
 */
case class Panel() extends ContainerComponent {

  private val panel = new JPanel

  panel.putClientProperty ("suit-wrapper", this)

  protected[suit] def wrapped = panel
  def className = "Panel"

  protected[suit] def wrappedContainer = panel
}
