/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit.Property

/**
 * @author Steven Dobay
 *
 * Dispatcher for polygons.
 */
case class Polygon_() {
  private var allCoords: List[Point] = List()
  private var pointCount = 0
  private var isFilled = false

  object coords {
    def +=(p: Point) = add(p)
    def add(p: Point) = allCoords = allCoords ++ List(p)
  }

  val points = Property[Int](pointCount = _)

  val filled = Property[Boolean](isFilled = _)

  /**
   * @return with the packed polygon
   */
  def pack() = new Polygon(allCoords.toArray, pointCount, isFilled)
}
