package suit.charts

import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PiePlot3D
import org.jfree.data.general.DefaultPieDataset
import org.jfree.util.Rotation

/**
 * @author Steven Dobay
 *
 * Creates a simple 2D pie chart. The chart's main parameters
 * can't be changed!
 *
 * @param chartTitle : initial title of the chart
 * @param dataSet    : list of label and values
 * @param ev1        : the values must be Numerics!
 * @tparam T
 */
case class PieChart[T: Numeric](
  chartTitle: String,
  dataSet: List[(String, T)],
  dimension: ChartDimension = _2D
) extends Chart {

  private val pieDataset = new DefaultPieDataset
  for(pair <- dataSet)
    pieDataset.setValue(pair._1, pair._2.asInstanceOf[Number])

  private val pie = if(dimension == _2D)
    ChartFactory.createPieChart(
      chartTitle, pieDataset, true, true, false
    )
  else {
    val chart = ChartFactory.createPieChart3D(
      chartTitle, pieDataset, true, true, false
    )
    /*
     * For recommended look and feel.
     */
    val plot: PiePlot3D = chart.getPlot.asInstanceOf[PiePlot3D]
    plot.setStartAngle(290)
    plot.setDirection(Rotation.CLOCKWISE)
    plot.setForegroundAlpha(0.5f)
    chart
  }

  protected[suit] def jFreeChart = pie

  def className = "PieChart"
}
