/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.event.DocumentEvent
import javax.swing.text.Document

/**
 * @author Steven Dobay
 *
 * Abstraction for all text components.
 */
abstract class TextComponentLike[T](private val initText: String = "")
 extends Bindable[T] {

  /**
   * @return with the swing document
   */
  def document: Document

  /**
   * Sets the swing document
   * @param doc
   */
  def document_=(doc: Document): Unit

  /**
   * @return with the caret's index
   */
  def caretPosition: Int

  /**
   * Sets the caret's position
   * @param pos
   */
  def caretPosition_=(pos: Int): Unit

  /**
   * @return with the text of the component
   */
  def text: String

  /**
   * Sets the component's text
   * @param str
   */
  def text_=(str: String): Unit

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = EditEvent
  protected type ChangeListenerType = DocumentListener

  /**
   * We can't reference this component from the listener -
   * that is a different object.
   */
  private val thisComponent = this

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit)
  : ChangeListenerType = {
    val listener = new ChangeListenerType {
      private var comp = thisComponent
      def component = comp

      override def insertUpdate(e: DocumentEvent): Unit =
        proc(createEditEvent(e.getLength, caretPosition)
          .copy(doneInsertion = true))

      override def changedUpdate(e: DocumentEvent): Unit =
        proc(createEditEvent(e.getLength, caretPosition)
          .copy(doneChange = true))

      override def removeUpdate(e: DocumentEvent): Unit =
        proc(createEditEvent(e.getLength, caretPosition)
          .copy(doneRemoval = true))
    }
    document.addDocumentListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType): Unit =
    document.removeDocumentListener(l)
}
