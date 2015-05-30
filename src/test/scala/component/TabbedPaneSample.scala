/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit._

/**
 * @author Steven Dobay
 */
object TabbedPaneSample extends DesktopApp("Sample for TabbedPane") {

  frame.size = Size(400, 300)

  frame.layout = layouts.BorderLayout()// it's interesting to see it with FlowLayout

  frame += new TabbedPane_() {
    for(i <- 1 to 3)
      withTab := Tab(s"Tab$i", Button(s"Button$i"))
  }
}
