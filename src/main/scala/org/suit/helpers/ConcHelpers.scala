/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.helpers

/**
 * @author Steven Dobay
 */
object ConcHelpers {

  class SimpleThread(fn: => Unit) extends Thread {
    override def run =
      try { fn }
      catch { case ex: InterruptedException => () }
  }

  object SimpleThread {
    def apply(fn: => Unit) = {
      val thread = new SimpleThread(fn)
      thread.start()
      thread
    }
  }

}