/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.{Graphics, GridBagConstraints}

/**
 * @author Steven Dobay
 *
 * Abstraction for all components which are containers.
 */
trait ContainerComponent extends Container with Component {

  protected var layoutModel: Option[Layout] = None
  private type ComponentDispatcher = Component_
  
  /**
   * @return with the layout.
   */
  def layout = layoutModel

  /**
   * Sets the layout model.
   * @param l
   */
  def layout_=(l: Layout) = {
    layoutModel = Some(l)
    wrapped.setLayout(l.wrapped)
  }

  /**
   * Adds the new component.
   * @param comp
   */
  def add(comp: Component): Unit = wrapped.add(comp.wrapped)

  /**
   * Adds the component to the layout with the given grid-bag constraints.
   * @param comp
   * @param constraint
   */
  def add(comp: Component, constraint: GridBagConstraints) =
    wrapped.add(comp.wrapped, constraint)

  /**
   * Adds a component with a border-layout constraint.
   * @param comp
   * @param constraint
   */
  def add(comp: Component, constraint: String) =
    wrapped.add(comp.wrapped, constraint)

  /**
   * Removs the component.
   * @param comp
   */
  def remove(comp: Component) = wrapped.remove(comp.wrapped)

  /**
   * Adds the new component.
   * @param comp
   */
  def +=(comp: Component): Unit = add(comp)

  /**
   * Adds the new component.
   * @param comp
   */
  def add(comp: ComponentDispatcher): Unit = add(comp.pack())

  /**
   * Adds the new component.
   * @param comp
   */
  def +=(comp: ComponentDispatcher): Unit = add(comp.pack())

  /**
   * Adds the component to the layout with the given grid-bag constraints.
   * @param comp
   * @param constraint
   */
  def add(comp: ComponentDispatcher, constraint: GridBagConstraints): Unit =
    add(comp.pack(), constraint)

  /**
   * Adds a component with a border-layout constraint.
   * @param comp
   * @param constraint
   */
  def add(comp: ComponentDispatcher, constraint: String): Unit =
    add(comp.pack(), constraint)

  /**
   * Removs the component.
   * @param comp
   */
  def -=(comp: Component) = remove(comp)



  /**
   * Removs the component.
   * @param comp
   */
  def -=(comp: ComponentDispatcher) = remove(comp.pack())

  /**
   * Removs the component.
   * @param comp
   */
  def remove(comp: ComponentDispatcher): Unit =
    remove(comp.pack())

  /**
   * Adds multiply components.
   * @param components
   */
  def ++=(components: Component*) =
    components.foreach(add _)

  /**
   * Adds multiply components.
   * @param components
   */
  def addAll(components: Component*) =
    components.foreach(add _)

  /**
   * Adds multiply components.
   * @param components
   */
  def ++=(components: List[ComponentDispatcher]) =
    components.foreach(c => add(c.pack()))

  /**
   * Adds multiply components.
   * @param components
   */
  def addAll(components: List[ComponentDispatcher]) =
    components.foreach(c => add(c.pack()))

  /**
   * Removes multiply components.
   * @param components
   */
  def --=(components: Component*) =
    components.foreach(remove _)

  /**
   * Removes multiply components.
   * @param components
   */
  def removeAll(components: Component*) =
    components.foreach(remove _)

  /**
   * Removes all components!!!
   */
  def clearComponentPalette() =
    components().foreach(remove _)
}