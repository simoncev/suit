/**
 * Copyright© Steven Dobay 2015
 */
package scala.test

import org.suit._

/**
 * Example for using UniLayout.
 *
 * @author Steven Dobay
 */
object UniLayoutExample extends DesktopApp("Example for UniLayout") {
  frame.hide()
  frame.size = Size(400, 300)

  frame.layout = layouts.UniLayout()

  import layouts.UniLayout._

  frame.add(Button("A"))
  frame.add(Button("B"), wrap())
  frame.add(Button("C"), cell(1, 1))
  frame.add(Button("D"), cell(1, 2))
  frame.add(Button("E"), wrap())
  frame.add(Button("F"), span(2, 2))
  frame.add(Button("G"))
  frame.add(Button("H"), south())

  frame.show()
}
