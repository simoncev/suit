/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */

import sframe._

/**
 * @author Steven Dobay
 */
object BasicUI extends App("Basic UI") {

  /**
   * Setting the size of the frame
   */
  content.size = Dim(300, 300)

  /**
   * Creating menus for the menubar
   */
  content.addMenus(
    /**
     * Adding a simple menu with a title; the "++=" operator
     * can add multiple menus as varargs
     *
     */
    Menu("General") ++=(

      /**
       * Adding a simple textmenu item with action by the "&>" operator
       */
      TextMenuItem("A") &> { mouseEvent =>
        popups.info(content, "A!")
        println("The A action!")
      },
      CheckBoxMenuItem("B") &> (_ => popups.warning(content, "NO BEEEEE!")),
      TextMenuItem("Class name") &> ( mouseEvent =>
         popups.info(content, "You clicked from a " +
                              mouseEvent.getSource.className + "!")//we can get the source class
        )
      ),
    Menu("Exit") ++= (
      TextMenuItem("Exit") &> (_ => popups.warning(content, "I can't exit!"))
      )
  )

  /**
   * Adding the simple label
   */
  content += Label("Take this: ")

  /**
   * Adding a simple button and an actionevent handler
   * with the '@>' operator(same as withAction)
   */
  content += Button("Click me!") @> { _ =>

    /**
     * Using the choice dialog from the popups
     */
    val res = popups.choice(content, "Choose: ", "Hello!",
      Array("A", "B", "C"), "A")
    println(res)
  }

  content += Button("Other Button!")
              .handleClick(_ => popups.info(content, "OH, MY CLICK EVENT!"))
              .handleMouseExit { _ =>
                popups.yesNo(content, "Could you handle the yesno?")
              }

  content += ListView("A", "B", "C").onSelection { (self, from, to) =>
       println("from " + from + " - to " + to)
  }

}