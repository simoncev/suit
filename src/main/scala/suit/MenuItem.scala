/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
abstract class MenuItem (protected var menuItem: JComponent)
  extends Component {

  /**
   * @return with the wrapped menu item
   */
  def wrapped = menuItem
}
