/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.media

import org.suit._
import java.util.concurrent.TimeUnit
import org.suit.helpers.ConcHelpers._
import akka.actor.{Props, ActorSystem, Actor}

/**
 * @author Steven Dobay
 * @version 0.9
 *
 * Simple controller which is capable of controlling an
 * implementation of MediaPlayer like VideoPlayer.
 */
case class MediaController(mediaPlayer: MediaPlayer)
  extends ContainerComponent {

  private val context = ActorSystem("MediaController")
  private val player = context.actorOf(Props(new MediaHandler()))
  private var isPlaying = mediaPlayer.isPlaying()
  private val panel = new Panel()
  panel.layout = layouts.FlowLayout()

  private val playBtn = new Button_("Play") {
    if(isPlaying) text := "Pause"
    onAction := ( _ => if(mediaPlayer.isMediaDefined()) {
      if (isPlaying) {
        text := "Play"
        player ! Pause()
      } else {
        text := "Pause"
        stopBtn.enable()
        player ! Play()
      }
    })
    container := panel
  }.pack()

  private val stopBtn: Button = new Button_("Stop") {
    enabled := false
    onAction := { _ =>
      enabled := false
      player ! Stop()
      playBtn.text = "Play"
    }
    container := panel
  }.pack()

  val soundSlider = new Slider_(0, 100) {
    onAction := (_ => player ! SetVolume() )
    container := panel
    value := 50
  }.pack()

  panel.mouseWheels += { e =>
    soundSlider.value = soundSlider.value -
      (e.preciseWheelRotation * 5).toInt
    player ! SetVolume()
  }

  soundSlider.keyEvents += { e =>
    soundSlider.value = soundSlider.value -
      (if(e.keyCode == KeyEvent.VK_LEFT) 5
      else if(e.keyCode == KeyEvent.VK_RIGHT) -5
      else 0)
    player ! SetVolume()
  }

  private val timerLabel = new Label("00:00:00")
  panel += timerLabel

  private val timeSlider = new Slider_() {
    min := 0
    max := 6000
    container := panel
  }.pack()

  timeSlider.keyEvents += { e =>
    var hasChange = true
    timeSlider.value = timeSlider.value - (
      if(e.keyCode == KeyEvent.VK_LEFT) 5
      else if(e.keyCode == KeyEvent.VK_RIGHT) -5
      else { hasChange = false; 0 } )
    if(hasChange) player ! Seek(timeSlider.value)
  }

  private val durationLabel = new Label("00:00:00")
  panel += durationLabel

  /**
   * Signals for actors.
   */
  private case class Play()
  private case class Pause()
  private case class Stop()
  private case class Seek(pos: Int)
  private case class SetVolume()

  private class MediaHandler() extends Actor {
    private var playHandler: Option[Thread] = None
    private var timerHandler: Option[Thread] = None
    private var seconds = 0

    private def reset() =
      if(playHandler.isDefined) {
        playHandler.get.interrupt()
        isPlaying = false
        timerHandler.get.interrupt()
        playHandler = None
        timerHandler = None
      }

    def receive = {
      case Play() => {
        playHandler = Some(SimpleThread(mediaPlayer.play()))
        timeSlider.max = (mediaPlayer.duration() / 1000).toInt
        isPlaying = true
        timerHandler = Some(SimpleThread (
          while(true) timeSlider.synchronized {
            Thread.sleep(1000)
            seconds += 1
            timeSlider.value = seconds
            timerLabel.text = timeToString(seconds)
          }
        ))

        durationLabel.text = timeToString(mediaPlayer.duration() / 1000)
      }
      case Pause() => { mediaPlayer.pause(); reset() }
      case Stop() => {
        mediaPlayer.stop()
        reset()
        seconds = 0
      }
      case Seek(pos) => {
        seconds = pos
        mediaPlayer.seek(pos, TimeUnit.SECONDS)
      }
      case SetVolume() =>
        mediaPlayer.volume = soundSlider.value
    }
  }

  /**
   * @param seconds : time in long seconds.
   * @return with the formatted text-time.
   */
  private def timeToString(seconds: Long): String = {
    val hours = seconds / 3600
    val minutes = (seconds - (hours * 3600)) / 60
    val secs = seconds - (hours * 3600) - (minutes * 60)

    def format(l: Long) =
      if(l == 0) "00" else if(l < 10) "0" + l else l.toString

    format(hours) + ":" + format(minutes) + ":" + format(secs)
  }

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() = panel.componentsSize()

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() =
    panel.wrapped.getComponents

  /**
   * @return with the name of the class
   */
  def className = "MediaController"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = panel.wrapped

  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer = panel.wrapped
}