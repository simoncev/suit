/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit._

/**
 * @author Steven Dobay
 */
object ProgressBarSample extends DesktopApp("Sample for progress bar!") {
  frame.hide()
  frame.size = Size(400, 300)

  val bar = ProgressBar(0, 100, 20)//min, max, value

  frame += bar
  frame.show()

  for(i <- 1 to 4) {
    popups.info("Info", s"Phase$i")
    bar.map(_ + 25)
  }
  popups.info("Info", "It\'s full!")
}
