/**
 * Copyright© by Steven Dobay on 2015
 */
package org.suit

import javax.swing.JScrollPane

/**
 * @author Steven Dobay
 * @param component
 */
case class ScrollPane[C <: Component](component: C)
   extends Component {
  private val pane = new JScrollPane(component.wrapped)

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = pane

  /**
   * @return with the name of the class
   */
  def className = "ScrollPane"
}
