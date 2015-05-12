/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 */
case class Series[T: Numeric](title: String, values: List[(T, T)])
