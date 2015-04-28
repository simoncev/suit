/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.layouts

import org.suit.layouts.UniLayout._

/**
 *
 * This layout depends on MigLayout: see http://www.miglayout.com/.
 * The layout's usage isn't similar to the original and
 * the implementation isn't complete. The simplicity and
 * the easy usage was in mind while implementing...
 *
 * @author Steven Dobay
 * @version 1.0
 * @since 0.9.7
 * @param columnConstraints : constraints for columns.
 * @param rowConstraints    : constraints for rows.
 */
case class UniLayout(
 columnConstraints: Option[Constraints] = None,
 rowConstraints: Option[Constraints] = None
) extends org.suit.Layout {

  private val layout = new net.miginfocom.swing.MigLayout(
   "",
   if(columnConstraints.isDefined)
     columnConstraints.get.createDefinition() else "",
   if(rowConstraints.isDefined)
     rowConstraints.get.createDefinition() else ""
  )
  protected[suit] def wrapped = layout
}

/**
 * Static classes and factories for UniLayout.
 */
object UniLayout {

   /**
    * @param columnConstraints
    * @param rowsConstraints
    * @return with a new UniLayout with column- & row constraints defined.
    */
  def apply(columnConstraints: Constraints,
            rowsConstraints: Constraints): UniLayout =
   UniLayout(Some(columnConstraints),
             Some(rowsConstraints))

  /**
   * @param constraints : a sequence of tuples which contains
   *                      the column- or row indexes(counted from 1),
   *                      the gap value and
          *               the size properties of the components(optional).
   * @param u    : implicit evidence for the gap's unit.
   */
  case class Constraints(constraints: Array[(Int, Int, Array[PureProperty])])
                        (implicit u: MigUnit) {

    /**
     * @return with the miglayout string for gaps' size
     *         between components.
     */
    protected[suit] def createDefinition(): String =
      if(!constraints.isEmpty) {
       val indexes = constraints map (_._1)
       var buffer = ""
       for(i <- 1 to indexes.max)
         if(indexes.contains(i)) {
          val curr = constraints.find(_._1 == i).get
          buffer += "[" +
                     (if(curr._3.isEmpty) "" else clean(curr._3)) + "]" +
                     (if(curr._2 > 0) curr._2 + u.unit() else "")
         } else buffer += "[]"
       buffer
      } else ""

    private def clean(arr: Array[PureProperty], buffer: String = ""): String =
     if(!arr.isEmpty) clean(arr.tail, buffer + " " + arr.head.get())
     else buffer
  }

  object Constraints {
    def apply(constraints: (Int, Int, List[PureProperty])*)
             (implicit U: MigUnit): Constraints =
     Constraints(constraints.map(x => (x._1, x._2, x._3.toArray)).toArray)
  }

  /**
   * Abstraction for miglayout properties.
   */
  trait UniLayoutProperty {
    def get(): String
  }

  /**
   * Same as UniLayoutProperty but these
   * properties can be used in the column- or row constraints
   * of the layout.
   */
  trait PureProperty extends UniLayoutProperty

  /**
   * Abstraction for the unit system.
   */
  trait MigUnit {
    import units._
    def unit() = this match {
      case px()      => "px"
      case mm()      => "mm"
      case cm()      => "cm"
      case inch()    => "inch"
      case percent() => "%"
      case dots()    => "dots"
      case logical() => "logical"
      case _         => ""
    }
  }

  /**
   * Contains the usable unit objects.
   */
  object units {
    case class px() extends MigUnit
    case class mm() extends MigUnit
    case class cm() extends MigUnit
    case class inch() extends MigUnit
    case class percent() extends MigUnit
    case class dots() extends MigUnit
    case class logical() extends MigUnit
  }

  /**
   * Adds wrapping:
   * The simple "wrap" puts the component
   * into a new line; the "wrap n" creates
   * n-columned lines.
   *
   * @param n : Option[Int]
   */
  case class wrap(n: Option[Int] = None) extends PureProperty {
    def get() = n match {
      case Some(x) => "wrap " + x.toString
      case _ => "wrap"
    }
  }

  object wrap {
    def apply(n: Int): wrap = wrap(Some(n))
  }

  /**
   * spans the spaces for the component.
   *
   * @param w
   * @param h
   */
  case class span(w: Option[Int] = None,
                  h: Option[Int] = None) extends UniLayoutProperty {
    def get() = "span" + ((w, h) match {
      case (Some(x), None) => " " + x.toString
      case (Some(x), Some(y)) => " " + x.toString + " " + y.toString
      case _ => ""
    })
  }

  object span {
    def apply(n: Int): span = span(Some(n))
    def apply(w: Int, h: Int): span = span(Some(w), Some(h))
  }

  /**
   * Puts the component into the given cell.
   *
   * @param row
   * @param column
   */
  case class cell(row: Int, column: Int) extends UniLayoutProperty {

    case class CellWithSize(pos: String, width: Int,
                            height: Int)(implicit u: MigUnit)
       extends UniLayoutProperty  {

      def get() = pos + " " + width + u.unit() + " " + height + u.unit()
    }

    def get() = "cell " + column + " " + row

    /**
     * Sets the size of the cell.
     *
     * @param w
     * @param h
     * @param u
     * @return with a new CellWithSize UniLayoutProperty.
     */
    def withSize(w: Int, h: Int)(implicit u: MigUnit) =
      CellWithSize(get(), w, h)
  }

  /**
   * Docking abstraction, dock places:
   *  east, west, north, south.
   */
  trait Docking extends UniLayoutProperty {
    def get() = this match {
      case east()  => "dock east"
      case west()  => "dock west"
      case north() => "dock north"
      case south() => "dock south"
      case _       => ""
    }
  }

  case class east() extends Docking
  case class west() extends Docking
  case class north() extends Docking
  case class south() extends Docking

  /**
   * An abstraction to handle resizing.
   * It can grow in width(growx), in height(growy), in both(grow)
   * and it can shrink or not(noshrink).
   */
  trait OnResize extends PureProperty {
    def get() = this match {
      case growx()    => "growx"
      case growy()    => "growy"
      case grow()     => "grow"
      case noshrink() => "shrink 0"
      case _          => ""
    }
  }
  case class growx() extends OnResize
  case class growy() extends OnResize
  case class grow() extends OnResize
  case class noshrink() extends OnResize

  /**
   * If one of these values is 0 it will be abandoned.
   *
   * @param min : minSize
   * @param optimal : preferredSize
   * @param max : maxSize
   */
  case class Sizes(min: Int, optimal: Int, max: Int) extends PureProperty {
    def get() = {
      var buffer = ""
      if (min != 0) buffer += min
      buffer += ":"
      if(optimal != 0) buffer += optimal
      buffer += ":"
      if(max != 0) buffer += max
      buffer
    }
  }

  /**
   * Data class for height sizes.
   * If one of these values is 0 it will be abandoned.
   *
   * @param min : minSize
   * @param optimal : preferredSize
   * @param max : maxSize
   */
  case class width(min: Int, optimal: Int, max: Int) extends UniLayoutProperty {
    def get() = "width " + Sizes(min, optimal, max).get()
  }

  /**
   * Data class for height properties.
   * If one of these values is 0 it will be abandoned.
   *
   * @param min : minSize
   * @param optimal : preferredSize
   * @param max : maxSize
   */
  case class height(min: Int, optimal: Int, max: Int) extends UniLayoutProperty {
    def get() = "height " + Sizes(min, optimal, max).get()
  }

  /**
   * Abstraction for positioning components in cells.
   * Align can be: left(), right(), top(), bottom() & center().
   */
  trait Align extends PureProperty {
    def get() = "align " + (this match {
      case left()   => "left"
      case right()  => "right"
      case top()    => "top"
      case bottom() => "bottom"
      case center() => "center"
    })
  }

  case class left() extends Align
  case class right() extends Align
  case class top() extends Align
  case class bottom() extends Align
  case class center() extends Align
}