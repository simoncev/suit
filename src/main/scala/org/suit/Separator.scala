/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JSeparator

/**
 * @author Steven Dobay
 */
case class Separator() extends Widget {

  private val sep = new JSeparator()

  sep.putClientProperty ("suit-wrapper", this)

  protected[suit] def wrapped = sep

  def className = "Separator"
}
