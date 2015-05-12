/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

/**
 * @author Steven Dobay
 */
case class TimeSeries[T: Numeric](lineTitle: String, values: List[(T, TimeUnit)])