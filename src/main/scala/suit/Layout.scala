/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

/**
 * @author Steven Dobay
 */
trait Layout {
  protected[suit] def wrapped: java.awt.LayoutManager
}
