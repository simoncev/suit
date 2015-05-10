/**
 * Copyright© Steven Dobay 2015
 */
package scala.test

import java.awt.Color

import org.suit._

/**
 * @author Steven Dobay
 */
object TransformerExample extends DesktopApp("Sample for using transformer method") {

  frame.size = Size(400, 300)

  /**
   * We can perform a formatting at every addition
   * with pattern matching if we use
   * method "transformer". Never forget to
   * handle unexpected components with "case _ => ()".
   * The benefits of this over "forall" is that
   * transformer splits the computation time.
   */
  frame.transformer = ( (c: Component) => c match {
    case _: Button => c.background = Color.BLUE
    case _: Label  => c.foreGround = Color.YELLOW
    case _ => ()
  })

  frame.layout = layouts.FlowLayout()

  frame += Label("This is a button: ")
  frame += Button("Hi!")
  frame += Spinner()
}
