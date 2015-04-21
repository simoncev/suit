/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import java.awt.Image
import java.awt.image.{ImageObserver => JImageObserver}

/**
 * @author Steven Dobay
 *
 *  This event is called when information about an image
 *  which was previously requested using an
 *  asynchronous interface becomes available.
 */
case class ImageObserver(source: Image,
                         rect: Rectangle,
                         infoFlags: Int)

object ImageObserver {
  /**
   * This flag in the infoflags argument to imageUpdate indicates
   * that the width of the base image is now available and
   * can be taken from the width argument to the imageUpdate callback method.
   */
  val WIDTH = JImageObserver.WIDTH

  /**
   * This flag in the infoflags argument to imageUpdate
   * indicates that the height of the base image is now available and can be
   * taken from the height argument to the imageUpdate callback method.
   */
  val HEIGHT = JImageObserver.HEIGHT
  /**
   * This flag in the infoflags argument to imageUpdate indicates
   * that an image which was being tracked asynchronously
   * was aborted before production was complete.
   */
  val ABORT = JImageObserver.ABORT

  /**
   * This flag in the infoflags argument to imageUpdate
   * indicates that a static image which was previously drawn
   * is now complete and can be drawn again in its final form.
   */
  val ALLBITS = JImageObserver.ALLBITS

  /**
   * This flag in the infoflags argument to imageUpdate indicates that an
   * image which was being tracked asynchronously has encountered an error.
   */
  val ERROR = JImageObserver.ERROR

  /**
   * This flag in the infoflags argument to imageUpdate indicates
   * that another complete frame of a multi-frame image
   * which was previously drawn is now available to be drawn again.
   */
  val FRAMEBITS = JImageObserver.FRAMEBITS

  /**
   * This flag in the infoflags argument to imageUpdate indicates
   * that the properties of the image are now available.
   */
  val PROPERTIES = JImageObserver.PROPERTIES

  /**
   * This flag in the infoflags argument to imageUpdate indicates that more
   * pixels needed for drawing a scaled variation of the image are available.
   */
  val SOMEBITS = JImageObserver.SOMEBITS
}