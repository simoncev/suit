/**
 * Copyright© Steven Dobay 2015
 */
package suit.charts

import java.util.Calendar
import org.jfree.data.time.RegularTimePeriod
import org.jfree.data.time.{
 Year => JYear,
 Month => JMonth,
 Week => JWeek,
 Day => JDay,
 Hour => JHour,
 Minute => JMinute,
 Second => JSecond,
 Millisecond => JMillisecond
}

/**
 * @author Steven Dobay
 *
 * Created to use in charts where time periods occurs in usage.
 */
trait TimeUnit {
  def time: RegularTimePeriod
}

case class Year(private val date: Calendar) extends TimeUnit {
  def time = new JYear(date.getTime)
}

case class Month(private val date: Calendar) extends TimeUnit {
  def time = new JMonth(date.getTime)
}

case class Week(private val date: Calendar) extends TimeUnit {
  def time = new JWeek(date.getTime)
}

case class Day(private val date: Calendar) extends TimeUnit {
  def time = new JDay(date.getTime)
}

case class Hour(private val date: Calendar) extends TimeUnit {
  def time = new JHour(date.getTime)
}

case class Minute(private val date: Calendar) extends TimeUnit {
  def time = new JMinute(date.getTime)
}

case class Second(private val date: Calendar) extends TimeUnit {
  def time = new JSecond(date.getTime)
}

case class Millisecond(private val date: Calendar) extends TimeUnit {
  def time = new JMillisecond(date.getTime)
}