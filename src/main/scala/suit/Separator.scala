/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JSeparator

/**
 * @author Steven Dobay
 */
case class Separator() extends Widget {

  private val sep = new JSeparator()

  sep.putClientProperty ("scala-frame-wrapper", this)

  protected[suit] def wrapped = sep

  def className = "Separator"
}
