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
 */
case class Picture(img: Image) {
  private var start: Point = Point(0, 0)
  private var dim: Size = Size(0, 0)
  private var bg = Color.WHITE

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
  def dimension(proc: ImageObserver => Boolean) =
    Size(img.getWidth(createImgListener(proc)),
        img.getHeight(createImgListener(proc)))

  /**
   * Sets the dimension(width, height)
   * @param dm
   */
  def dimension(dm: Size) = dim = dm

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

object Picture {
  /**
   * Reads the image from the path.
   *
   * @param path
   * @return with the picture initialized from it.
   */
  def apply(path: String): Picture =
   Picture(ImageIO.read(new File(path)))

  /**
   * Make a picture out from another one;
   * this just copies the image instance -
   * the properties won't be included!
   *
   * @param pic
   */
  def apply(pic: Picture): Picture = Picture(pic.img)

  /**
   * Creates a picture from an url.
   * @param url
   * @return with a new picture
   */
  def apply(url: URL): Picture = Picture(ImageIO.read(url))
}
