/**
 * Copyright© Steven Dobay 2015
 */
package charts

import java.util.GregorianCalendar

import suit._
import suit.charts._

/**
 * @author Steven Dobay
 */
object TimeChartSample extends App("Timechart Sample") {

  frame.size = Dim(800, 600)

  val chart = TimeChart[Int]("My Time Chart", "X", "Y") // chart's title with the titles of x and y axises
    .withSeries("Series1", List(                  // data-set #1
     (10, Day(new GregorianCalendar(2014, 1, 1))),
     (12, Day(new GregorianCalendar(2014, 1, 2))),
     (11, Day(new GregorianCalendar(2014, 1, 3))),
     (13, Day(new GregorianCalendar(2014, 1, 4)))
    ))
    .withSeries("Series2", List(                  // data-set #2
     (13, Day(new GregorianCalendar(2014, 1, 1))),
     (12, Day(new GregorianCalendar(2014, 1, 2))),
     (14, Day(new GregorianCalendar(2014, 1, 3))),
     (10, Day(new GregorianCalendar(2014, 1, 4)))
    ))

  frame += new ChartPanel(chart)
}
