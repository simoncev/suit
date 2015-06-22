/**
 * Copyright© by Steven Dobay on 2015
 */
package component

import java.awt.Color

import org.suit._

/**
 * @author Steven Dobay
 */
object ListViewSample extends DesktopApp("Sample for ListView") {

  frame.size = Size(400, 300)

  val lw = new ListView_("Red", "Green", "Blue") {
     selectionMode := ListView.SINGLE_SELECTION

     layoutOrientation := ListView.HORIZONTAL_WRAP

     onSelection := { (l, i, j) =>
        println(l.value(i))
        l.background = l.value(i) match {
          case "Red"   => Color.RED
          case "Green" => Color.GREEN
          case _       => Color.BLUE
        }
     }
  }

  frame += ScrollPane(lw.pack())

}
