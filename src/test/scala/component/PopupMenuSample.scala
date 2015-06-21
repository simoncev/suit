/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit._

/**
 * @author Steven Dobay
 */
object PopupMenuSample extends DesktopApp("Sample for popup menus") {

  frame.size = Size(400, 300)

  val btn: Button = new Button_("Change my text!") {
    popupMenu := new PopupMenu_() {
      newItems := List("Text A", "Text B", "Text C")
                   .map(t => TextMenuItem(t) #> (btn.text = t))
    }.pack()
  }.pack()

  frame += btn

}
