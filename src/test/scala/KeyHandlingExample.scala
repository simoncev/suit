/**
 * Copyright© Steven Dobay 2015
 */
package scala.test

import org.suit._

/**
 * @author Steven Dobay
 */
object KeyHandlingExample extends DesktopApp("Example for key event handling") {

  frame.size = Size(400, 300)

  frame += new Label("Press left- or right arrow to change this slider's value!")
  val slider = new Slider(1, 10)
  frame += slider

  frame.keyEvents += ( e =>
     e.keyCode match {
       case KeyEvent.VK_LEFT =>
         slider.value = slider.value - 1
       case KeyEvent.VK_RIGHT =>
         slider.value = slider.value + 1
     }
    )
}
