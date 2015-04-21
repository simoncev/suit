/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

/**
 * @author Steven Dobay
 */
trait ContainerComponent extends Container with Component {

  def +=(comp: Component) = wrapped.add(comp.wrapped)
  def -=(comp: Component) = wrapped.remove(comp.wrapped)

  def ++=(components: Component*) =
    for(comp <- components) wrapped.add(comp.wrapped)

  def --=(components: Component*) =
    for(comp <- components) wrapped.remove(comp.wrapped)
}
