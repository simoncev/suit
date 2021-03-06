/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.charts

import java.util.GregorianCalendar

import org.suit._
import org.suit.charts._

/**
 * @author Steven Dobay
 */
object TimeChartWithDispatcher extends DesktopApp("Time chart with dispatcher") {

  frame.size = Size(800, 600)

  val chart = new TimeChart_[Int]("My Time Chart") {
    xTitle := "X"
    yTitle := "Y"

    dataSet += new TimeSeries(
      "Series1", List(
      (10, Day(new GregorianCalendar(2014, 1, 1))),
      (12, Day(new GregorianCalendar(2014, 1, 2))),
      (11, Day(new GregorianCalendar(2014, 1, 3))),
      (13, Day(new GregorianCalendar(2014, 1, 4)))
    ))

    dataSet += new TimeSeries(
      "Series2", List(
      (13, Day(new GregorianCalendar(2014, 1, 1))),
      (12, Day(new GregorianCalendar(2014, 1, 2))),
      (14, Day(new GregorianCalendar(2014, 1, 3))),
      (10, Day(new GregorianCalendar(2014, 1, 4)))
    ))
  }

  frame += ChartPanel(chart)
}
