/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit._

/**
 * @author Steven Dobay
 */
object SubmenuSample extends DesktopApp("Sample for submenu") {

  frame.size = Size(400, 300)


  frame.addMenus (
    Menu("General") ++= (
     Menu("More general") ++=
      TextMenuItem("Surprise...") @> popups.info("!", "But not today")
    ),
    Menu("Other") ++=
      TextMenuItem("What?") @> popups.info("?", "What?")
  )
}
