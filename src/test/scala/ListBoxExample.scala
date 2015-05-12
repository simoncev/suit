/**
 * Copyright© Steven Dobay 2015
 */
package scala.test

import org.suit.{popups, ListBox, Size}

/**
 * @author Steven Dobay
 */
object ListBoxExample extends org.suit.DesktopApp("Example for list box") {

  frame.size = Size(300, 300)

  val list = ListBox[String]("A", "B", "C", "D")
  frame += list

  list.changeEvents += { e =>
     if(e.newSelection) {
       popups.info("Whooo!", "You selected: " + e.selectedItem.get)
       list.items += e.selectedItem.get
     }
     else
       popups.warning("NOOOO!", "You deselected the previous!")
  }

}
