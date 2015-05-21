/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.component

import org.suit.{DesktopApp, ListBox, Size, TableBuilder}

/**
 * @author Steven Dobay
 */
object TableExample extends DesktopApp("Table Example") {
  frame.hide()

  frame.size = Size(800, 600)

  case class User(id: Int, name: String, var address: String, var year: Int)
  val users = List(User(1, "Steve", "AS", 1993), User(2, "Anita", "AA", 1996))

  val table = new TableBuilder {
    columnNames = List("Name", "Address", "Year")
    rows = 10
    columns = 3 // we ignore the ids

    def isEditableAt(row: Int, column: Int) =
      if(column >= 1 && row <= users.size) true else false

    def valueAt(row: Int, column: Int) =
      if(row < users.size) {
        val u = users(row)
        if (column == 0) u.name
        else if(column == 1) u.address
        else u.year
      } else null

    val lb = ListBox[Any]()
    for(i <- 1900 to 2015) lb.items += i // showing the years in listboxes
    createListBoxColumn(2, lb) // we can't change the data because
                               // there is no definition for that.

    // but here is one for changing the address and the year:
    onChange((v, i, j) => if(j == 1) {
      users(i).address = v.toString
    } else if(j == 2) {
      users(i).year = v.asInstanceOf[Int]
    })
  }

  frame += table.buildTable // this method builds the model and the table - DON'T FORGET TO USE!!!

  frame.show()
}
