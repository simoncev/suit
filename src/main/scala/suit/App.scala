/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

/**
 * @author Steven Dobay
 */
class App(private val initTitle: String) {

  val frame = new Frame(initTitle)
  var arguments = Array[String]()

  implicit val popupContainer = frame

  def onStart(): Unit = {}

  def main(args: Array[String]) = {
    frame.show
    arguments = args
    onStart
  }

}
