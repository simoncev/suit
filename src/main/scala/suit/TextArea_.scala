/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to TextArea
 */
abstract class TextArea_(private val txt: String = "")
   extends Component_ {
  private val area = new TextArea(txt)

  def pack() = area

  val text = Property[String](area.text = _)

  val columns = Property[Int](area.columns = _)

  val rows = Property[Int](area.rows = _)

  val lineWrap = Property[Boolean](area.lineWrap(_))

  val tabSize = Property[Int](area.tabSize = _)

  val wrapStyleWord = Property[Boolean](area.wrapStyleWord = _)
}
