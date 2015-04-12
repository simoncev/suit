/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package scala.test

import java.awt.Color

import suit._

/**
 * @author Steven Dobay
 */
object BasicUI extends App("Basic UI") {

  /**
   * Setting the layout model
   */
  frame.layout = layouts.GridLayout(4, 2)

  /**
   * Setting the size of the frame
   */
  frame.size = Dim(300, 300)

  /**
   * Creating menus for the menubar
   */
  frame.addMenus(
    /**
     * Adding a simple menu with a title; the "++=" operator
     * can add multiple menus as varargs
     *
     */
    Menu("General") ++= (

      /**
       * Adding a simple textmenu item with action by the "@>" operator
       */
      TextMenuItem("A") @> { mouseEvent =>
        popups.info(frame, "A!")
        println("The A action!")
      },
      CheckBoxMenuItem("B") @> (_ => popups.warning(frame, "NO BEEEEE!")),
      TextMenuItem("Class name") @> ( mouseEvent =>
         popups.info(frame, "You clicked from a " +
                              mouseEvent.source.className + "!")//we can get the source class
        )
      ),
    Menu("Exit") ++= (
      TextMenuItem("Exit") @> (_ => popups.warning(frame, "I can't exit!"))
      )
  )

  /**
   * Adding the simple label
   */
  frame += Label("Take this: ")

  /**
   * Adding a simple button and an actionevent handler
   * with the '@>' operator(same as withAction)
   */
  frame += Button("Click me!") @> { _ =>

    /**
     * Using the choice dialog from the popups
     */
    val res = popups.choice(frame, "Choose: ", "Hello!",
      Array("A", "B", "C"), "A")
    println(res)
  }

  frame += Button("Other Button!")
              .handleClick(_ => popups.info(frame, "OH, MY CLICK EVENT!"))
              .handleMouseExit { _ =>
                popups.yesNo(frame, "Could you handle the yesno?")
              }

  frame += ListView("A", "B", "C").onSelection { (self, from, to) =>
       println("from " + from + " - to " + to)
  }

  /**
   * Example for using tree-syntax with property-based dispatch
   */
  frame += new Label_("Fancy label!") {
    text := "I changed the text!"
    foreGround := Color.BLUE
  }.pack

  frame += new PasswordField_() {
    foreGround := Color.RED
    preferredSize := Dim(100, 20)
  }.pack

  frame += new CheckBox_() {
    text := "Click!"
    onChange := (_ => popups.info(frame, "Don't!"))
  }.pack()
}