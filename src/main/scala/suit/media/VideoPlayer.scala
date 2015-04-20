/**
 * Copyright© Steven Dobay 2015
 */
package suit.media

import java.io.File
import java.net.URL

/**
 * @author Steven Dobay
 *
 * If you're a ubuntu user you probably need an ffmpeg codec:
 * gstreamer0.10-ffmpeg(you can get that from ppa:mc3man/trusty-media
 * if you use trusty).
 * @param url(Option) : the url of the source
 *                     (because of the factory, it can be a File)
 */
case class VideoPlayer(private val url: Option[URL] = None)
   extends MediaPlayer(url) with suit.ContainerComponent {

  private val component = new org.gstreamer.swing.VideoComponent()
  playBin2.setVideoSink(component.getElement)
  playBin2.setVisualization(component.getElement)

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() = component.getComponentCount

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() = component.getComponents

  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer = component

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = component

  /**
   * @return with the name of the class
   */
  def className = "VideoPlayer"
}

/**
 * For VideoPlayer instantiations.
 */
object VideoPlayer {
  def apply(url: URL): VideoPlayer = VideoPlayer(Some(url))
  def apply(file: File): VideoPlayer = VideoPlayer(Some(file.toURI.toURL))
}