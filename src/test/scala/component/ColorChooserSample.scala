/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit.{ColorChooser, Button, Size, DesktopApp}

/**
 * @author Steven Dobay
 */
object ColorChooserSample extends DesktopApp("Sample for Color-choosers") {

  frame.size = Size(400, 300)

  val btn = Button("Change my text with the other button!")

  frame += btn

  frame += Button("Color changer") #> (
    btn.background = ColorChooser.run(frame)
  )
}
