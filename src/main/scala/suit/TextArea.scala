/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Rectangle
import javax.swing.JTextArea

/**
 * @author Steven Dobay
 */
case class TextArea(private val initText: String = "") extends Component {
  private val area = new JTextArea(initText)

  def text = area.getText()
  def text_=(str: String) = area.setText(str)

  def +(str: String, pos: Int) = area.insert(str, pos)
  def insert(str: String, pos: Int) = area.insert(str, pos)

  def ++(str: String) = area.append(str)

  def append(str: String) = area.append(str)

  def columns = area.getColumns
  def columns_=(c: Int) = area.setColumns(c)

  def rows = area.getRows
  def rows_=(r: Int) = area.setRows(r)

  def lines() = area.getLineCount

  def lineOfOffset(offset: Int) = area.getLineOfOffset(offset)

  def lineStartOffset(line: Int) =
    area.getLineStartOffset(line)

  def lineEndOffset(line: Int) =
    area.getLineEndOffset(line)

  def lineWrap(b: Boolean) = area.setLineWrap(b)
  def isLineWrap() = area.getLineWrap

  def tabSize = area.getTabSize
  def tabSize_=(s: Int) = area.setTabSize(s)

  def preferredScrollableViewportSize() =
    area.getPreferredScrollableViewportSize

  def scrollableTracksViewPortWidth() =
    area.getScrollableTracksViewportWidth

  def scrollableUnitIncrement(r: Rectangle, a: Int, b: Int) =
    area.getScrollableUnitIncrement(r, a, b)

  def wrapStyleWord = area.getWrapStyleWord

  def wrapStyleWord_=(word: Boolean) =
    area.setWrapStyleWord(word)

  def wrapped = area
  def className = "TextArea"
}
