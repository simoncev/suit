/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import java.awt.{Color, Image}
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import suit.{Property, Size}

/**
 * @author Steven Dobay
 *
 * Dispatcher for pictures
 *
 * @param initImg : ImageResource
 */
case class Picture_(private var initImg: ImageResource) {
  private var initBG = Color.WHITE
  private var initPoint = Point(0, 0)
  private var initSize = Size(0, 0)

  val background = Property[Color](initBG = _)
  val startPoint = Property[Point](initPoint = _)
  val size = Property[Size](initSize = _)

  /**
   * @return with the initialized Picture.
   */
  def pack() = {
    val p = new Picture(initImg)
    p.size(initSize)
    p.background = initBG
    p.startPoint = initPoint
    p
  }
}