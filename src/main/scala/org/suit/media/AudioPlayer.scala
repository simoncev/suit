/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.media

import java.io.File
import java.net.URL

import org.gstreamer.ElementFactory

/**
 * @author Steven Dobay
 *
 * @param url(Option) : the url of the source
 *                     (because of the factory, it can be a File)
 */
case class AudioPlayer(private val url: Option[URL] = None)
  extends MediaPlayer(url) {

  // throwing away unnecessary video data
  playBin2.setVideoSink(ElementFactory.make("fakesink", "videosink"));

  /**
   * @return with the name of the class
   */
  def className = "AudioPlayer"
}

/**
 * For AudioPlayer instantiations.
 */
object AudioPlayer {
  def apply(url: URL): AudioPlayer = AudioPlayer(Some(url))
  def apply(file: File): AudioPlayer = AudioPlayer(Some(file.toURI.toURL))
}