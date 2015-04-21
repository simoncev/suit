/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.helpers

import javax.swing.JComponent

import org.suit.Component

/**
 * @author Steven Dobay
 */
protected[suit] object ComponentHelpers {

  final implicit class ComponentFromJComponent(c: JComponent) {
    def readSuitComponent =
      c.getClientProperty("suit-wrapper").asInstanceOf[Component]
  }

}
