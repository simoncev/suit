/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.media

import java.net.URL
import java.util.concurrent.TimeUnit
import org.gstreamer._
import org.gstreamer.elements.PlayBin2

/**
 * @author Steven Dobay
 * Lightweight media player for suit based on gstreamer-java.
 * You need an installed version of gstreamer below 1.0 and jna!
 *
 * @param initURL : the url of the source.
 */
abstract class MediaPlayer(private var initURL: Option[URL] = None) { self =>
  Gst.init("MediaPlayer", Array())
  protected val playBin2 = new PlayBin2("MediaPlayer")
  if(initURL.isDefined) playBin2.setURI(initURL.get.toURI)

  /**
   * Starts the video.
   */
  def play() = {
    playBin2.setState(State.PLAYING)
    playBin2.play()
    Gst.main()
  }

  /**
   * Starts the player.
   * @return with the Player.
   */
  def withPlaying(): self.type = {
    play()
    self
  }

  /**
   * Pause the player.
   */
  def pause() = playBin2.setState(State.PAUSED)

  /**
   * Stops the player.
   */
  def stop() = playBin2.setState(State.NULL)

  /**
   * @return with the mediaplayer's state
   */
  def getState() = playBin2.getState

  /**
   * @return the frame rate of the video's sink
   */
  def sinkFrameRate() = playBin2.getVideoSinkFrameRate

  /**
   * @return true if the media player has started playing.
   */
  def isPlaying() = playBin2.isPlaying

  /**
   * Gives the source's url to the player.
   * @param url
   */
  def urlLocation_=(url: URL) = {
    this.initURL = Some(url)
    playBin2.setURI(url.toURI)
  }

  /**
   * @return with the url-location
   */
  def urlLocation = initURL

  /**
   * @return true if media is defined.
   */
  def isMediaDefined() = initURL.isDefined

  /**
   * @return with the percent of the volume.
   */
  def volume = playBin2.getVolumePercent

  /**
   * Sets the volume.
   * @param percent
   */
  def volume_=(percent: Int) = playBin2.setVolumePercent(percent)

  /**
   * @return with the position in millis
   */
  def position = playBin2.queryPosition(TimeUnit.MILLISECONDS)

  /**
   * Sets the position in millis.
   * @param time
   * @return
   */
  def position_=(time: Long) = playBin2.seek(time, TimeUnit.MILLISECONDS)

  /**
   * @param unit
   * @return with the time position formatted by the given
   *         TimeUnit.
   */
  def position(unit: TimeUnit) = playBin2.queryPosition(unit)

  /**
   * Sets the position.
   * @param time
   * @param unit
   */
  def seek(time: Long, unit: TimeUnit) = playBin2.seek(time, unit)

  /**
   * @return with the duration as millis.
   */
  def duration() = playBin2.queryDuration(TimeUnit.MILLISECONDS)
}
