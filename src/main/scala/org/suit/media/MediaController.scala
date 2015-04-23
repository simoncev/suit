/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.media

import org.suit._
import akka.actor.{Props, ActorSystem, Actor}

/**
 * @author Steven Dobay
 *
 * Simple controller which is capable of controlling an
 * implementation of MediaPlayer like VideoPlayer.
 */
case class MediaController(mediaPlayer: MediaPlayer)
   extends ContainerComponent {

  private val panel = new Panel()
  panel.layout = layouts.FlowLayout()

  private val context = ActorSystem("MediaController")
  private val comps = new ActorComponents()

  private class ActorComponents() {
    import scala.concurrent.ExecutionContext.Implicits.global

    private val playBtn = new Button("Play")
    playBtn @> (_ => playHandler ! Start())
    panel += playBtn

    private val stopBtn = new Button("Stop") @> (_ => stopHandler ! Stop())
    stopBtn.disable()
    panel += stopBtn

    private val soundSlider = new Slider(0, 100)
    soundSlider.value = 50
    panel += soundSlider

    val player = context.actorOf(Props(new MediaPlayerHandler()), "player")
    val playHandler = context.actorOf(Props(new PlayHandler), "playHandler")
    val stopHandler = context.actorOf(Props(new StopHandler), "stopHandler")

    val volumeController = new VolumeController()
    volumeController.start()

    /**
     * Message types for actors to control thair behaviors.
     */
     case class Start()
     case class Pause()
     case class Stop()
     case class Text(txt: String)
     case class SetVolume(percent: Int)
     case class Enabled(flag: Boolean)

    /**
     * Handler for the media player.
     */
     class MediaPlayerHandler() extends Actor {
      class PlayThread extends Thread {
        override def run = mediaPlayer.play()
      }
      private var playThread: Option[PlayThread] = None

      def closePlayThread() = if(playThread.isDefined) {
        playThread.get.join(100)
        playThread = None
      }
      def receive = {
        case Start() => if(playThread.isEmpty) {
          val thread = new PlayThread()
          playThread = Some(thread)
          thread.start()
        }
        case Pause() => { closePlayThread(); mediaPlayer.pause() }
        case Stop()  => { closePlayThread(); mediaPlayer.stop() }
        case SetVolume(p) => mediaPlayer.volume = p
      }
    }

    /**
     * Handler for play/pause buttons.
     */
     class PlayHandler() extends Actor {
      def receive = {
        case Start() if (mediaPlayer.isMediaDefined()) => {
          if (playBtn.text == "Play") {
            playBtn.text = "Pause"
            stopHandler ! Enabled(true)
            player ! Start()
          } else {
            playBtn.text = "Play"
            player ! Pause()
          }
        }
        case Stop() => {
          playBtn.text = "Play"
          player ! Stop()
          stopHandler ! Enabled(false)
        }
      }
    }

    /**
     * Handler for stopbutton.
     */
     class StopHandler() extends Actor {
      def receive = {
        case Stop() => playHandler ! Stop()
        case Enabled(true)  => stopBtn.enable()
        case Enabled(false) => stopBtn.disable()
      }
    }

    /**
     * Handler for the volume.
     */
     class VolumeController extends Thread {
      override def run = {
        player ! SetVolume(50)
        panel.mouseWheels += { e =>
          soundSlider.value = soundSlider.value -
                     (e.preciseWheelRotation * 5).toInt
          player ! SetVolume(soundSlider.value)
        }
      }
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