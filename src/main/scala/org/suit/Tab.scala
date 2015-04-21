/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.Icon

/**
 * @author Steven Dobay
 */
case class Tab(title: String, component: Component,
               icon: Option[Icon] = None,
               toolTip: Option[String] = None)
