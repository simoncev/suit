/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.Property

/**
 * @author Steven Dobay
 *
 * Dispatcher for polygons.
 */
case class Polyline_() {
  private var allCoords: List[Point] = List()
  private var pointCount = 0

  object coords {
    def +=(p: Point) = add(p)
    def add(p: Point) = allCoords = allCoords ++ List(p)
  }

  val points = Property[Int](pointCount = _)

  protected[suit] def pack() = new Polyline(allCoords.toArray, pointCount)
}
