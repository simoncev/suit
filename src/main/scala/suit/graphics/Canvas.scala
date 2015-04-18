/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import java.awt.{Graphics, Font, Color}
import suit._
import javax.swing.JPanel

/**
 * @author Steven Dobay
 */
case class Canvas() extends Component {

  private var panel = new JPanel()
  private var objects: List[Graphics => Unit] = List()

  /**
   * Draws the panel
   * @return with the class
   */
  def doDrawing() = {
    panel = new JPanel() {
      override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
      objects.foreach(_.apply(g))
     }
    }
    this
  }

  /**
   * Buffers the new image-drawing function
   * to the "wish list".
   */
  private def add(fn: Graphics => Unit) =
    objects = objects ++ List(fn)

  /**
   * Sets the current color
   * @param c
   */
  def color(c: Color) = add(_.setColor(c))

  /**
   * Sets the current font
   * @param f
   */
  def font(f: Font) = add(_.setFont(f))

  /**
   * Clears the space on the specified rect.
   *
   * @param start
   * @param dim
   */
  def clear(start: Point, dim: Size) =
    add(_.clearRect(start.x, start.y, dim.width(), dim.height()))

  /**
   * Copies an area with the distance specified by dx and dy
   * @param rec
   * @param dx
   * @param dy
   */
  def copyTo(rec: Rectangle, dx: Int, dy: Int) =
    add(_.copyArea(rec.x(), rec.y(), rec.width(),
                     rec.height(), dx, dy))

  /**
   * Draws a rectangle.
   * @param r : Rectangle
   */
  def +=(r: Rectangle): Unit  = add { graphic =>
    val (x, y, width, height, filled) = (r.x(), r.y(), r.width(),
                                         r.height(), r.filled)

    r match {
      case Rectangle2D(_, _, _) =>
        if(filled) graphic.fillRect(x, y, width, height)
        else graphic.drawRect(x, y, width, height)
      case Rectangle3D(_, _, _, raised) =>
        if(filled) graphic.fill3DRect(x, y, width, height, raised)
        else graphic.draw3DRect(x, y, width, height, raised)
      case RoundRectangle(_, _, arc, _) =>
        if(filled) graphic.fillRoundRect(x, y, width, height,
                                         arc.width(), arc.height())
        else graphic.drawRoundRect(x, y, width, height,
                                   arc.width(), arc.height())
      case _=> ()
    }
  }

  /**
   * Draws the new rectangle.
   * @param rect
   */
  def +=(rect: Rectangle_): Unit  = this.+=(rect.pack())

  /**
   * Draws a circle.
   * @param c : Circle
   */
  def +=(c: Circle): Unit = add { graphic =>
    val (x, y, width, height, startAngle, arcAngle) =
      (c.x(), c.y(), c.width(), c.height(), c.startAngle, c.arcAngle)

    if(c.filled) graphic.fillArc(x, y, width, height, startAngle, arcAngle)
    else graphic.drawArc(x, y, width, height, startAngle, arcAngle)
  }

  /**
   * Adds a new circle.
   * @param c
   */
  def +=(c: Circle_): Unit = this.+=(c.pack())

  /**
   * Draws and oval.
   * @param o : Oval
   */
  def +=(o: Oval): Unit = add { graphic =>
    val (x, y, width, height) = (o.x(), o.y(), o.width(), o.height())
    if(o.filled()) graphic.fillOval(x, y, width, height)
    else graphic.drawOval(x, y, width, height)
  }

  /**
   * Adds the new oval.
   * @param o
   */
  def +=(o: Oval_): Unit = this.+=(o.pack())

  /**
   * Draws a polygon.
   * @param pol
   */
  def +=(pol: Polygon): Unit = add { graphic =>
    val (xs, ys) = (pol.coords.map(_.x), pol.coords.map(_.y))
    if(pol.filled) graphic.fillPolygon(xs, ys, pol.points)
    else graphic.drawPolygon(xs, ys, pol.points)
  }

  /**
   * Adds the new polygon.
   * @param pol
   */
  def +=(pol: Polygon_): Unit = this.+=(pol.pack())

  /**
   * Draws a polyline.
   * @param pol
   */
  def +=(pol: Polyline): Unit = add { graphic =>
    graphic.drawPolyline(pol.coords.map(_.x),
                         pol.coords.map(_.y), pol.points)
  }

  /**
   * Adds the new polyline
   * @param pol
   */
  def +=(pol: Polyline_): Unit = this.+=(pol.pack())

  /**
   * Draws an image with the specified parameters
   * in Picture.
   * @param pic : Picture
   */
  def +=(pic: Picture): Unit = add (pic.draw(_))

  /**
   * Draws an image with the specified parameters
   * in Picture_
   * @param pic
   */
  def +=(pic: Picture_): Unit = add(pic.pack().draw(_))

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = panel

  /**
   * @return with the name of the class
   */
  def className = "Canvas"
}