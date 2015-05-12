/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import java.awt.Image
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

/**
 * @author Steven Dobay
 *
 * Abstraction for all image resources.
 */
trait ImageResource {
  /**
   * @return with the image-object
   */
 def image(): Image
}

/**
 * @param url : URL
 */
case class URLResource(url: URL) extends ImageResource {
  def image() = ImageIO.read(url)
}

 /**
  * @param img : java.awt.Image
  */
case class JImageResource(img: Image) extends ImageResource {
  def image() = img
}

/**
 * @param pic : Picture
 */
case class PictureResource(pic: Picture) extends ImageResource {
  def image() = pic.img
}

/**
 * @param path : String
 */
case class ImagePath(path: String) extends ImageResource {
  def image() = ImageIO.read(new File(path))
}

/**
 * @param file : java.io.File
 */
case class ImageFile(file: File) extends ImageResource {
  def image() = ImageIO.read(file)
}