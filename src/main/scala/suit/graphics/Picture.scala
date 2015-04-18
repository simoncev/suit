/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import java.awt.{Color, Graphics, Image}
import java.awt.image.{ImageObserver => JImageObserver, ImageConsumer, ImageProducer}
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import suit.Size

/**
 * @author Steven Dobay
 *
 * @param res : ImageResource
 */
case class Picture(res: ImageResource) {
  private var start: Point = Point(0, 0)
  private var dim: Size = Size(0, 0)
  private var bg = Color.WHITE

  val img = res.image()

  /**
   * @return with the start-point
   */
  def startPoint = start

  /**
   * Sets the start point
   * @param p
   */
  def startPoint_=(p: Point) = start = p

  /**
   * @return with the dimension
   */
  def size(proc: ImageObserver => Boolean) =
    Size(img.getWidth(createImgListener(proc)),
        img.getHeight(createImgListener(proc)))

  /**
   * Sets the dimension(width, height)
   * @param dm
   */
  def size(dm: Size) = dim = dm

  /**
   * @return with the background's color
   */
  def background = bg

  /**
   * Sets the background's color
   * @param c
   */
  def background_=(c: Color) = bg = c

  /**
   * Draws the image to the graphic-panel
   * @param g
   */
  protected[suit] def draw(g: Graphics) =
    g.drawImage(img, start.x, start.y, dim.width(), dim.height(),
                bg, createImgListener(_ => true))

  /**
   * Shorthand to create image-change listeners from events.
   */
  private def createImgListener(proc: ImageObserver => Boolean) =
    new JImageObserver {
      override def imageUpdate(img: Image, infoflags: Int, x: Int, y: Int,
                               width: Int, height: Int): Boolean =
        proc(ImageObserver(img, new Rectangle2D(Point(x, y),
          Size(width, height)),
          infoflags))
    }
}
