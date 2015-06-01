/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit._

/**
 * @author Steven Dobay
 */
object TableExample extends DesktopApp("Table Example") {
  frame.hide()

  frame.size = Size(800, 600)

  case class User(id: Int, name: String, var address: String, var year: Int)
  val users = List(User(1, "Steve", "AS", 1993), User(2, "Anita", "AA", 1996))

  val table = new TableBuilder {
    columnClasses = List(classOf[String], classOf[String], classOf[Int])
    columnNames = List("Name", "Address", "Year")
    rows = 3
    columns = 3 // we ignore the ids

    def isEditableAt(row: Int, column: Int) = column > 0

    def valueAt(row: Int, column: Int) =
      if(row < users.size) users(row).productElement(column + 1)
      else ""

    val lb = ListBox[Any]()
    for(i <- 1900 to 2015) lb.items += i // showing the years in listboxes
    createListBoxColumn(2, lb) // we can't change the data because
                               // there is no definition for that.

    // but here is one for changing the address and the year:
    onChange((v, i, j) =>
      if (j == 1) users(i).address = v.toString
      else if (j == 2) users(i).year = v.toString.toInt
    )
  }

  // method "buildTable" builds the model and the table - DON'T FORGET TO USE!!!
  // method "withScrollPane()" helps to show the columns' name
  frame += table.buildTable.withScrollPane()

  frame.show()
}
