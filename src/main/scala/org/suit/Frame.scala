/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.GridBagConstraints
import javax.swing._

/**
 * @author Steven Dobay
 */
case class Frame(private val initTitle: String) extends ContainerComponent {
  private val jframe = new JFrame(initTitle)
  private var dim = Size(0, 0)
  private var menuBar: Option[JMenuBar] = None
  private var isMenuBarInitialized = false

  /**
   * Initialization
   */
  show()
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  layout = layouts.FlowLayout()

  /**
   * Sets the layout model.
   * @param l
   */
  override def layout_=(l: Layout) = {
    layoutModel = Some(l)
    jframe.setLayout(l.wrapped)
  }

  /**
   * Adds the new component.
   * @param comp
   */
  override def add(comp: Component): Unit =
    jframe.add(formatter(comp).wrapped)

  /**
   * Adds the component to the layout with the given grid-bag constraints.
   * @param comp
   * @param constraint
   */
  override def add(comp: Component, constraint: GridBagConstraints) =
    jframe.add(formatter(comp).wrapped, constraint)

  /**
   * Adds a component with a border-layout constraint.
   * @param comp
   * @param constraint
   */
  override def add(comp: Component, constraint: String) =
    jframe.add(formatter(comp).wrapped, constraint)

  /**
   * Removs the component.
   * @param comp
   */
  override def remove(comp: Component) =
    jframe.remove(comp.wrapped)

  /**
   * Initializes the menubar
   */
  def createMenuBar() = {
    menuBar = Some(new JMenuBar)
    jframe.setJMenuBar(menuBar.get)
    isMenuBarInitialized = true
    menuBar.get.setVisible(true)
  }

  /**
   * Deletes the menubar
   */
  def deleteMenuBar(): Unit = {
    var bar = jframe.getJMenuBar()
    bar = null
    isMenuBarInitialized = false
    updateUI()
  }

  /**
   * @return with an array of the menus
   */
  def menus =
    menuBar.get.getComponents
               .map { comp =>
                 val jMenu = comp.asInstanceOf[JMenu]
                 val newMenu = Menu("")
                 newMenu.wrapped = jMenu
                 newMenu
               }

  /**
   * Adds the menu to the menubar if exist and create it if not
   * @param menu
   */
  def addMenu(menu: Menu): Unit =
    if(isMenuBarInitialized)
      menuBar.get.add(menu.wrapped)
    else {
      createMenuBar()
      addMenu(menu)
    }

  /**
   * Adds the menus to the menubar
   * @param menus
   */
  def addMenus(menus: Menu*): Unit =
    for(m <- menus) addMenu(m)

  /**
   * Removes the given menu
   * @param menu
   */
  def removeMenu(menu: Menu): Unit =
   if(isMenuBarInitialized) menuBar.get.remove(menu.wrapped)

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() =
    jframe.getContentPane.getComponents

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    jframe.getComponentCount

  /**
   * @return with the size of the Frame
   */
  def size = Size(jframe.getSize)

  /**
   * Sets the size of the Frame
   * @param d
   */
  def size_=(d: Size) = {
    dim = d
    jframe.setSize(dim)
  }

  /**
   * @return with the title of the Frame
   */
  def title = jframe.getTitle

  /**
   * Sets the title of the Frame
   * @param t
   */
  def title_=(t: String) = jframe.setTitle(t)

  /**
   * Makes the frame visible.
   */
  override def show() = jframe.setVisible(true)

  /**
   * Hides the frame.
   */
  override def hide() = jframe.setVisible(false)

  /**
   * Sets the exit operation
   * @param op
   */
  def onExit_=(op: Int) =
    jframe.setDefaultCloseOperation(op)

  /**
   * @param ix
   * @return with the component at the given index as optional
   */
  def apply(ix: Int): Option[Component] =
   if(ix < jframe.getComponentCount)
     Some(jframe.getComponent(ix).asInstanceOf[Component])
   else None

  /**
   * Updates the UI by revalidating and repainting
   */
  def updateUI() = {
    jframe.revalidate()
    jframe.repaint()
  }

  /**
   * @return with the wrapped JFrame
   */
  protected[suit] def wrappedContainer = jframe

  /**
   * @return with the name of the class
   */
  def className = "Frame"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = jframe.getRootPane
}

object Frame {
  val EXIT = JFrame.EXIT_ON_CLOSE
}
