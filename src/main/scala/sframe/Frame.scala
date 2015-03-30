/**
 * Copyright© Steven Dobay 2015
 */
package sframe

import javax.swing.{JFrame, JMenuBar, JMenu}

/**
 * @author Steven Dobay
 */
case class Frame(private val initTitle: String) extends Container {
  private var jframe = new JFrame(initTitle)
  private var dim = Dim(0, 0)
  private var layoutM: Layout = layouts.FlowLayout()
  private var menuBar: Option[JMenuBar] = None
  private var isMenuBarInitialized = false

  /**
   * Initialization
   */
  show
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  jframe.setLayout(layoutM.getWrapped)


  /**
   * Initializes the menubar
   */
  def createMenuBar() = {
    menuBar = Some(new JMenuBar)
    jframe.setJMenuBar(menuBar.get)
    isMenuBarInitialized = true
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
   * @return with the size of the Frame
   */
  def size = Dim(jframe.getSize)

  /**
   * Sets the size of the Frame
   * @param d
   */
  def size_=(d: Dim) = {
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
   * Adds the component tot the Frame and updates the ui
   * @param comp
   */
  def +=(comp: Component) = {
    jframe.getContentPane.add(comp.getWrapped)
    comp.setContainer(this)
    updateUI
  }

  /**
   * Removes the component from the Frame
   * @param comp
   */
  def -=(comp: Component) = {
    jframe.getContentPane.remove(comp.getWrapped)
    comp.setContainer(this)
    updateUI
  }

  /**
   * Adds the component to the Frame
   * @param comp
   */
  def add(comp: Component): Unit = {
    jframe.getContentPane.add(comp.getWrapped)
    comp.setContainer(this)
  }

  /**
   * Removes the component from the Frame and updates the UI
   * @param comp
   */
  def remove(comp: Component) = {
    jframe.getContentPane.remove(comp.getWrapped)
    comp.setContainer(this)
  }

  /**
   * Sets the exit operation
   * @param op
   */
  def onExit_=(op: Int) =
    jframe.setDefaultCloseOperation(op)

  /**
   * @return with the components of the frame in an array
   */
  def components =
    jframe.getComponents.map(_.asInstanceOf[Component])

  /**
   * @param ix
   * @return with the component at the given index as optional
   */
  def apply(ix: Int): Option[Component] =
   if(ix < jframe.getComponentCount)
     Some(jframe.getComponent(ix).asInstanceOf[Component])
   else None

  /**
   * Sets the layout strategy
   * @param layout
   */
  def layout_=(layout: Layout) = {
    layoutM = layout
    jframe.setLayout(layout.getWrapped)
  }

  /**
   * @return with a pointer to the layout manager
   */
  def layout = layoutM

  /**
   * Updates the UI by revalidating and repainting
   */
  def updateUI() = {
    jframe.revalidate()
    jframe.repaint()
  }

  /**
   * Makes the frame visible
   */
  def show() = jframe.setVisible(true)

  /**
   * Makes the frame invisible
   */
  def hide() = jframe.setVisible(false)

  /**
   * @return with the wrapped JFrame
   */
  def getWrapped = jframe
}

object Frame {
  val EXIT = JFrame.EXIT_ON_CLOSE
}
