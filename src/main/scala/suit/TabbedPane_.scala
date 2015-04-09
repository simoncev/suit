/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.Icon

/**
 * @author Steven Dobay
 */
abstract class TabbedPane_ extends Component_ {

  private val pane = new TabbedPane

  def pack() = pane

  val iconAt = Property[(Int, Icon)](
    t => pane.iconAt(t._1, t._2)
  )

  val componentAt = Property[(Int, Component)](t =>
    pane.componentAt(t._1, t._2)
  )

  val withTab = Property[Tab](pane += _)

  val withTabs = Property[List[Tab]](tabs =>
    for(tab <- tabs) pane += tab
  )
}
