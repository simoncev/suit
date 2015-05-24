/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import java.net.URL

import org.suit.layouts.BorderLayout
import org.suit.{Editor, Size, DesktopApp}

/**
 * @author Steven Dobay
 */
object EditorExample extends DesktopApp("Editor Example") {
  frame.hide()

  frame.size = Size(800, 600)
  frame.layout = BorderLayout()

  val editor = Editor()

  editor.page = new URL("http://www.scala-lang.org")

  frame.add(editor, BorderLayout.CENTER)

  frame.show()
}
