/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit.{RadioButton, Size, RadioButtonGroup, DesktopApp}

/**
 * @author Steven Dobay
 */
object RadioButtonGroupSample extends DesktopApp("Sample for RadioButtonGroup") {

  frame.size = Size(400, 300)

  val group = RadioButtonGroup().withButtons()

  val buttons = for(i <- 1 to 5) yield RadioButton(s"Button$i.")
  buttons.foreach(group += _)

  frame += group
}