/**
 * Copyright© by Steven Dobay on 2015
 */
package org.suit.layouts

import org.suit.{Component, Container}
import javax.swing.{GroupLayout => GL}

/**
 * A minimal wrapper for javax.swing.GroupLayout.
 *
 * @author Steven Dobay
 * @param container
 * @version 0.8
 */
case class GroupLayout(container: Container) extends org.suit.Layout {
  private val cont =  container.wrappedContainer.asInstanceOf[java.awt.Container]
  private val layout = new GL(cont)

  /**
    * @return with true if the creation of container-gaps is automatic.
    */
  def autoCreateContainerGaps = layout.getAutoCreateContainerGaps

  /**
   * @param flag
   */
  def autoCreateContainerGaps_=(flag: Boolean) =
    layout.setAutoCreateContainerGaps(flag)

  /**
   * @return with true if gap-creation is automatic.
   */
  def autoCreateGaps = layout.getAutoCreateGaps

  /**
   * @param flag
   */
  def autoCreateGaps_=(flag: Boolean) =
    layout.setAutoCreateGaps(flag)

  /**
   * Wrapper of a parallel(!) group.
   * @param alignment
   */
  class Group(alignment: GL.Alignment) {
    private val group = layout.createParallelGroup(alignment)
    protected[suit] def wrapped = group

    /**
     * Adds a new component.
     * @param c
     */
    def add(c: Component): Unit = group.addComponent(c.wrapped)

    /**
     * Same as add.
     */
    def += = add _
  }

  /**
   * @return with a new parallel group.
   */
  def createGroup(alignment: GL.Alignment = GL.Alignment.CENTER) =
    new Group(alignment)

  /**
   * Abstraction for the horizontal- and vertical groups.
   */
  abstract class Grouper {
    protected val group = layout.createSequentialGroup()

    /**
     * Adds a new component.
     * @param c
     */
    def add(c: Component) = group.addComponent(c.wrapped)

    /**
     * Same as add.
     */
    def += = add _

    /**
     * Adds a new parallel group.
     * @param g
     */
    def addGroup(g: Group) = group.addGroup(g.wrapped)

    /**
     * Same as addGroup
     */
    def ++= = addGroup _

    /**
     * Builds the main group.
     */
    def build(): Unit
  }

  /**
   * Manager of the horizontal group.
   */
  object horizontalGroup extends Grouper {
    def build() = layout.setHorizontalGroup(group)
  }

  /**
   * Manager of the vertical group.
   */
  object verticalGroup extends Grouper {
    def build() = layout.setVerticalGroup(group)
  }

  /**
   * @param flag
   */
  def honorsVisibility_=(flag: Boolean) =
    layout.setHonorsVisibility(flag)

  def honorsVisibility = layout.getHonorsVisibility

  /**
   * @return with the wrapped layout-manager.
   */
  protected[suit] def wrapped = layout
}

/**
 * Holder of contants.
 */
object GroupLayout {

  val DEFAULT_SIZE = GL.DEFAULT_SIZE
  val PREFERRED_SIZE = GL.PREFERRED_SIZE

  val BASELINE = GL.Alignment.BASELINE
  val CENTER = GL.Alignment.CENTER
  val TRAILING =  GL.Alignment.TRAILING
  val LEADING = GL.Alignment.LEADING
}
