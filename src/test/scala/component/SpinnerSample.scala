/**
 * Copyright© by Steven Dobay on 2015
 */
package component

import org.suit._

/**
 * @author Steven Dobay
 */
object SpinnerSample extends DesktopApp("Sample for Spinner") {

  frame.size = Size(400, 300)

  frame += new Spinner_() {
     onChange := ( ev =>
         popups.info("Info", "The value of the spinner is " +
                     ev.source.asInstanceOf[Spinner].value)
       )
  }

}
