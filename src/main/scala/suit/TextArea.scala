/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Rectangle
import javax.swing.JTextArea
import javax.swing.text.Document

/**
 * @author Steven Dobay
 */
case class TextArea(private val initText: String = "")
   extends TextComponentLike[String](initText) {

  type Wrapped = JTextArea

  private val area = new JTextArea(initText)

  area.putClientProperty("suit-wrapper", this)

  /**
   * @return with the swing document
   */
  def document: Document = area.getDocument

  /**
   * Sets the swing document
   * @param doc
   */
  def document_=(doc: Document): Unit = area.setDocument(doc)

  /**
   * @return with the caret's index
   */
  def caretPosition: Int = area.getCaretPosition

  /**
   * Sets the caret's position
   * @param pos
   */
  def caretPosition_=(pos: Int): Unit =
   area.setCaretPosition(pos)

  /**
   * @return with the text of the component
   */
  def text: String = area.getText

  /**
   * Sets the component's text
   * @param str
   */
  def text_=(str: String): Unit = area.setText(str)

  /**
   * Inserts text at position
   * @param str
   * @param pos
   */
  def +(str: String, pos: Int) = area.insert(str, pos)

  /**
   * Same as "+"
   * @param str
   * @param pos
   */
  def insert(str: String, pos: Int) = area.insert(str, pos)

  /**
   * Appends text
   * @param str
   */
  def ++(str: String) = area.append(str)

  /**
   * Same as "++"
   * @param str
   */
  def append(str: String) = area.append(str)

  /**
   * @return with the number of columns
   */
  def columns = area.getColumns

  /**
   * Sets the column number
   * @param c
   */
  def columns_=(c: Int) = area.setColumns(c)

  /**
   * @return with the number of rows
   */
  def rows = area.getRows

  /**
   * Sets the row's number
   * @param r
   */
  def rows_=(r: Int) = area.setRows(r)

  /**
   * @return with the number of lines
   */
  def lines() = area.getLineCount

  /**
   * @param offset
   * @return with the offset of the line at the given index
   */
  def lineOfOffset(offset: Int) = area.getLineOfOffset(offset)

  /**
   * @param line
   */
  def lineStartOffset(line: Int) =
    area.getLineStartOffset(line)

  /**
   * @param line
   */
  def lineEndOffset(line: Int) =
    area.getLineEndOffset(line)

  /**
   * Sets line-wrapping
   * @param b
   */
  def lineWrap(b: Boolean) = area.setLineWrap(b)

  /**
   * @return true if line-wrapping is set
   */
  def isLineWrap() = area.getLineWrap

  /**
   * @return with the size of the tab
   */
  def tabSize = area.getTabSize

  /**
   * Sets the tab's size
   * @param s
   */
  def tabSize_=(s: Int) = area.setTabSize(s)

  /**
   * @return with the size of the preferred scrollable viewport
   */
  def preferredScrollableViewportSize() =
    area.getPreferredScrollableViewportSize

  /**
   * @return with th width of the scrollable tracks-viewport
   */
  def scrollableTracksViewPortWidth() =
    area.getScrollableTracksViewportWidth

  /**
   * @param r : a Rectangle
   * @param a : an integer
   * @param b : an integer
   * @return with the scrollable unit-increment
   */
  def scrollableUnitIncrement(r: Rectangle, a: Int, b: Int) =
    area.getScrollableUnitIncrement(r, a, b)

  /**
   * @return true if wrap-style-word is enabled
   */
  def wrapStyleWord = area.getWrapStyleWord

  /**
   * Sets wrap-style-word mode
   * @param word
   */
  def wrapStyleWord_=(word: Boolean) =
    area.setWrapStyleWord(word)

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = area

  /**
   * @return with the name of the class
   */
  def className = "TextArea"

  /**
   * @return with the text of the component
   */
  def componentValue() = text
}
