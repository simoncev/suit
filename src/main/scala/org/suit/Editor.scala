/**
 * Copyright© by Steven Dobay on 2015
 */
package org.suit

import java.io.InputStream
import java.net.URL
import javax.swing.event.{HyperlinkEvent, HyperlinkListener}
import javax.swing.{JScrollPane, JEditorPane}
import javax.swing.text.{EditorKit, Document}

/**
 * @author Steven Dobay
 */
case class Editor() extends TextComponentLike[String]("") with Component {
  private val editor = new JEditorPane()
  private var scroller: Option[JScrollPane] = None

  /**
   * @return with the url of the editor's page.
   */
  def page = editor.getPage

  /**
   * Sets the editor's page.
   * @param url
   */
  def page_=(url: URL) = editor.setPage(url)

  /**
   * Sets the page's content.
   * @param str
   */
  def page_=(str: String) = editor.setPage(str)

  /**
   * @return with the content type of the document
   */
  def contentType = editor.getContentType

  /**
   * Sets the content type.
   * @param str
   */
  def contentType_=(str: String) = editor.setContentType(str)

  /**
   * @return with the swing document
   */
  def document: Document = editor.getDocument

  /**
   * Sets the swing document
   * @param doc
   */
  def document_=(doc: Document) = editor.setDocument(doc)

  /**
   * Creates a scroll pane for the editor.
   */
  def addScroller() =
    if(scroller.isEmpty) scroller = Some(new JScrollPane(editor))

  /**
   * @return with the scroll pane of the editor(if defined).
   */
  def getScroller() = scroller

  /**
   * @return with the caret's index
   */
  def caretPosition: Int = editor.getCaretPosition

  /**
   * Sets the caret's position
   * @param pos
   */
  def caretPosition_=(pos: Int): Unit =
    editor.setCaretPosition(pos)

  /**
   * @return with the text of the component
   */
  def text: String = editor.getText

  /**
   * Sets the component's text
   * @param str
   */
  def text_=(str: String): Unit = editor.setText(str)

  /**
   * @return with the editorkit
   */
  def editorKit = editor.getEditorKit

  /**
   * Sets the editorkit.
   * @param ek
   */
  def editorKit_=(ek: EditorKit) = editor.setEditorKit(ek)

  /**
   * Binds the value to the component.
   * @param value
   */
  protected def setValue(value: String): Unit =
    editor.setText(value)

  /**
   * @return with the component's value
   */
  def componentValue() = editor.getText

  /**
   * "This method initializes from a stream.
   * If the kit is set to be of type HTMLEditorKit,
   * and the desc parameter is an HTMLDocument,
   * then it invokes the HTMLEditorKit to initiate the read.
   * Otherwise it calls the superclass method which loads the model as plain text."
   *
   * @param in
   * @param description
   */
  def readFromStream(in: InputStream, description: AnyRef) =
    editor.read(in, description)

  /**
   * @param str
   * @return
   */
  def editorKitForContentType(str: String) =
    editor.getEditorKitForContentType(str)

  /**
   * @param str
   * @param editorkit
   */
  def editorKitForContentType(str: String, editorkit: EditorKit) =
    editor.setEditorKitForContentType(str, editorkit)

  def scrollableTracksViewportHeight() =
    editor.getScrollableTracksViewportHeight

  def scrollableTracksViewportWidth() =
    editor.getScrollableTracksViewportWidth

  /**
   * Manager of hyperlink events.
   */
  object hyperLinkEvents extends EventManager[HyperLinkEvent => Unit, HyperlinkListener] {

    /**
     * Create a new listener
     * @param proc
     * @return with the created listener
     */
    protected def createAndAddListener(proc: HyperLinkEvent => Unit) = {
       val listener = new HyperlinkListener {
         override def hyperlinkUpdate(hyperlinkEvent: HyperlinkEvent): Unit =
           proc(HyperLinkEvent(hyperlinkEvent))
       }
      editor.addHyperlinkListener(listener)
      listener
    }

    /**
     * Removes the listener from the registration places
     * @param l
     */
    protected def removeListener(l: HyperlinkListener): Unit =
      editor.removeHyperlinkListener(l)
  }

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = editor

  /**
   * @return with the name of the class
   */
  def className = "Editor"
}