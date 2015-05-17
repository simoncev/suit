/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.layouts

import org.suit._

/**
 * @author Steven Dobay
 * @version 1.0
 *
 * Wrapper for java's spring-layout.
 */
case class SpringLayout() extends Layout {
  private val layout = new javax.swing.SpringLayout()

  /**
   * @param constraint1 : use a constant from SpringLayout._
   * @param comp1
   * @param value
   * @param constraint2 : use a constant from SpringLayout._
   * @param comp2
   */
  def addConstraint(constraint1: String, comp1: Component,
                    value: Int, constraint2: String, comp2: Component): Unit =
    layout.putConstraint(constraint1, comp1.wrapped, value,
                         constraint2, comp2.wrapped)

  /**
   * @param constraint : a SpringLayout constant.
   * @param comp
   * @return
   */
  def getConstraint(constraint: String, comp: Component) =
    layout.getConstraint(constraint, comp.wrapped)

  /**
   * Removes a component.
   * @param comp
   */
  def remove(comp: Component) = layout.removeLayoutComponent(comp.wrapped)

  /**
   * @param comp
   * @return
   */
  def constraints(comp: Component) = layout.getConstraints(comp.wrapped)

  /**
   * @return with the wrapped layout manager.
   */
  protected[suit] def wrapped = layout
}

/**
 * Contains the constants imported and wrapped from javax.swing.SpringLayout.
 */
object SpringLayout {
  import javax.swing.{SpringLayout => SL}

  val NORTH = SL.NORTH
  val SOUTH = SL.SOUTH
  val WEST = SL.WEST
  val EAST = SL.EAST
  val BASELINE = SL.BASELINE
  val WIDTH = SL.WIDTH
  val HEIGHT = SL.HEIGHT
  val HORIZONTAL_CENTER = SL.HORIZONTAL_CENTER
}
