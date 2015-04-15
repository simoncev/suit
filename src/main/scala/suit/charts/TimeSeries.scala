/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

/**
 * @author Steven Dobay
 */
case class TimeSeries[T: Numeric](lineTitle: String, values: List[(T, TimeUnit)])