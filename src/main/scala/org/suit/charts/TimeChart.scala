/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.charts

import java.text.SimpleDateFormat
import org.jfree.chart.ChartFactory
import org.jfree.chart.axis.DateAxis
import org.jfree.data.time.{TimeSeries => JTimeSeries, TimeSeriesCollection}

/**
 * @author Steven Dobay
 *
 * Creates a simple time chart(only two dimensional supported!).
 *
 * @param initTitle : title
 * @param xTitle    : title of the x axis
 * @param yTitle    : title of the y axis
 * @param ev1       : the values must be Numerics
 * @tparam T
 */
case class TimeChart[T: Numeric](
  initTitle: String,
  xTitle: String,
  yTitle: String
) extends Chart {

  private val collection = new TimeSeriesCollection()
  private var timeChart = create()

  /**
   * @param lineTitle
   * @param values
   * @return with the chart with the new series added
   */
  def withSeries(lineTitle: String, values: List[(T, TimeUnit)]) = {
    val series = new JTimeSeries(lineTitle)
    for(v <- values) series.add(v._2.time, v._1.asInstanceOf[Number])
    collection.addSeries(series)
    this
  }

  /**
   * @param lineTitle
   * @param values
   * @return with the chart with the new series added
   */
  def ++(lineTitle: String, values: List[(T, TimeUnit)]) =
    withSeries(lineTitle, values)

  /**
   * Adds a new time series
   * @param series : TimeSeries
   * @return with the chart with the new series added
   */
  def withSeries(series: TimeSeries[T]): TimeChart[T] =
    withSeries(series.lineTitle, series.values)

  /**
   * Adds a new time series
   * @param series : TimeSeries
   * @return with the chart with the new series added
   */
  def ++(series: TimeSeries[T]) =
    withSeries(series.lineTitle, series.values)

  /**
   * @param dataset : a List of TimeSeries
   * @return with the chart.
   */
  def withSeries(dataset: List[TimeSeries[T]]): TimeChart[T] = {
    for(series <- dataset) withSeries(series)
    this
  }

  /**
   * @param format
   * @return with the fully initialized chart.
   */
  def createChart(format: String = "yyyy-MM-dd") = {
    timeChart = create()
    val plot = timeChart.getXYPlot
    val axis = plot.getDomainAxis.asInstanceOf[DateAxis]
    axis.setDateFormatOverride(new SimpleDateFormat(format))
    this
  }

  private def create() =
    ChartFactory.createTimeSeriesChart(
      initTitle, xTitle, yTitle, collection,
      true, true, false
    )

  protected[suit] def jFreeChart = timeChart
}