/**
 * Copyright© by Steven Dobay on 2015
 */
package component

import org.suit._

/**
 * @author Steven Dobay
 */
object SliderSample extends DesktopApp("Slider Sample") {

  frame.size = Size(400, 300)

  frame += new Slider_(0, 100) {
    onChange := ( ev =>
      popups.info("Info", "The value of the slider is " +
        ev.source.asInstanceOf[Slider].value)
    )
  }

}
