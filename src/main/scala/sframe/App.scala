/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

/**
 * @author Steven Dobay
 */
class App(private val initTitle: String) {

  val content = new Frame(initTitle)
  var arguments = Array[String]()

  def onStart(): Unit = {}

  def main(args: Array[String]) = {
    content.show
    arguments = args
    onStart
  }

}
