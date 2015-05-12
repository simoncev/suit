/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
abstract class Panel_ extends Component_ {
  private val panel = new Panel

  val newComponent = Property[Component](panel += _)

  val newComponents = Property[List[Component]](
    for(comp <- _) panel += comp
  )

  def pack() = panel
}
