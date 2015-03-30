/**
 * Copyright© Steven Dobay 2015
 */
package sframe

import java.awt.Color
import java.awt.event.MouseListener
import javax.swing.JLabel

/**
 * @author Steven Dobay
 */
case class Label(private val initTitle: String) extends Component {
  private val label = new JLabel(initTitle)
  private var container: Option[Container] = None

  label.putClientProperty("scala-frame-wrapper", this)

  /**
   * @return with the label's text
   */
  def text = label.getText

  /**
   * Sets the label's text
   * @param t
   */
  def text_=(t: String) = label.setText(t)

  /**
   * @return with the label's background
   */
  def background = label.getBackground

  /**
   * Sets the label's background
   * @param background
   */
  def background_=(background: Color) =
    label.setBackground(background)

  /**
   * @return with the name of the class
   */
  def className = "Label"

  /**
   * @return with a pointer to the parent optionally
   */
  def getContainer = container

  /**
   * @param c
   */
  def setContainer(c: Container) = container = Some(c)

  /**
   * @return with a pointer to the wrapped JComponent
   */
  def getWrapped = label

  /**
   * @param proc
   */
  override
  def onAction(proc: ActionEvent => Unit): Unit =
    label.addMouseListener(
      new MouseHandler().handleClick(e => proc(e.toActionEvent)).create
    )

  /**
   * @param key
   * @return with the property at the given key
   */
  def property(key: String) = label.getClientProperty(key)

  /**
   * Puts the property to the client property
   */
  def property_=(key: String, value: AnyRef) =
    label.putClientProperty(key, value)

  override def equals(obj: Any) =
    if(obj.isInstanceOf[Button])
      obj.asInstanceOf[Button].getWrapped == getWrapped
    else if(obj.isInstanceOf[JLabel])
      obj.asInstanceOf[JLabel] == getWrapped
    else false
}
