/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package scala.test

import java.awt.Color
import org.suit._

/**
 * @author Steven Dobay
 */
object BasicUI extends DesktopApp("Basic UI") {

  // Setting the layout model
  frame.layout = layouts.GridLayout(4, 2)

   //Setting the size of the frame
  frame.size = Size(500, 400)

   //Creating menus for the menubar
  frame.addMenus(
    /**
     * Adding a simple menu with a title; the "++=" operator
     * can add multiple menus as varargs
     */
    Menu("General") ++= (

      /**
       * Adding a simple textmenu item with action by the "@>" operator
       */
      TextMenuItem("A") @> { mouseEvent =>
        popups.info("", "A!")
        println("The A action!")
      },
      CheckBoxMenuItem("B") @> (_ => popups.warning("Warning!", "NO BEEEEE!")),
      TextMenuItem("Class name") @> ( mouseEvent =>
         popups.info("", "You clicked from a " +
                              mouseEvent.source.className + "!")//we can get the source class
        )
      ),
    Menu("Exit") ++= (
      TextMenuItem("Exit") @> (_ => popups.warning("Warning!", "I can't exit!"))
      )
  )

  // Adding the simple label
  frame += Label("Take this: ")

  /**
   * Adding a simple button and an actionevent handler
   * with the '@>' operator(same as withAction)
   */
  frame += Button("Click me!") @> { _ =>

    // Using the choice dialog from the popups
    val res = popups.choice("Choose: ", "Hello!",
                            Array("A", "B", "C"), "A")
    println(res)
  }

  frame += ListView("A", "B", "C").onSelection { (self, from, to) =>
       println("from " + from + " - to " + to)
  }

   // Example for using tree-syntax with property-based dispatch
  frame += new Label_("Fancy label!") {
    text := "I changed the text!"
    foreGround := Color.BLUE
  }
  frame.show()
  frame += new PasswordField_() {
    foreGround := Color.RED
    preferredSize := Size(100, 20)
  }

  frame += new CheckBox_() {
    text := "Click!"
    onChange := (_ => popups.info("Info:", "Don't!"))
  }

  frame += new TextField_() {
    onChange := (_ =>
      popups.info("", "It's hard to write like this, isn't?")
      )
  }

  frame += new Button("Just me, Button!")

  /**
   * Makes all buttons' color blue
   * in this frame and its sub-components.
   */
  frame.forall(Set("Button"), { comp: Button =>
    comp.background = Color.BLUE
  })

}
