/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JComponent
import scala.reflect.runtime.universe._

/**
 * @author Steven Dobay
 */
trait Container {
  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer: java.awt.Component

  /**
   * @return with all components.
   */
  protected[suit] def allComponents(): Array[java.awt.Component]

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize(): Int

  /**
   * @return with an array of components
   */
  def components(): Array[Component] =
    allComponents().flatMap { comp =>
      if(comp == null) Array[Component]()
      else {
        val wrapper = comp.asInstanceOf[JComponent]
                          .getClientProperty("suit-wrapper")

        if(wrapper == null) Array[Component]()
        else Array(wrapper.asInstanceOf[Component])
      }
    }

  /**
   * @return with the number of components
   */
  def componentsCount() = componentsSize()

  /**
   * Runtime-mirror for type-reflecting.
   * It is really expensive to instantiate.
   */
  private val mirror = runtimeMirror(getClass.getClassLoader)

  /**
   * Executes the styling function for all components
   * which class-name equals to the given one;
   * if the class-name is an empty string then
   * it executes for all.
   * This method has a cost if you use it too often...
   *
   * @param classNames : the names of the classes
   * @param styler     : the styler function
   * @tparam T         : the component's type
   */
  def forall[T <: Component](classNames: Set[String],
                             styler: T => Unit)
                            (implicit tag: TypeTag[T]): Unit = {
      components().foreach { c: Component =>

      val isDesiredType = mirror.reflect(c).symbol ==
                          typeOf[T].typeSymbol
      val isClassDefined = classNames.exists(c.styleClasses.contains(_))
      val isForAll = classNames.contains("")

      if((isDesiredType && (isClassDefined || isForAll)))
         styler(c.asInstanceOf[T])
      if(c.isInstanceOf[Container])
         c.asInstanceOf[Container].forall[T](classNames, styler)
    }
  }

  /**
   * Executes the styling function for all components
   * which class-name equals to the given one;
   * if the class-name is an empty string then
   * it executes for all.
   * This method has a cost if you use it too often...
   *
   * @param className : the name of the class
   * @param styler    : the styler function
   * @tparam T        : the component's type
   */
  def forall[T <: Component](className: String,
                             styler: T => Unit)
                            (implicit tag: TypeTag[T]): Unit =
   forall(Set(className), styler)

}
