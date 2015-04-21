/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.{JComponent, Icon, JTabbedPane}

/**
 * @author Steven Dobay
 */
case class TabbedPane()
  extends Component with Container {

  private val tabs = new JTabbedPane()

  tabs.putClientProperty ("suit-wrapper", this)

  /**
   * Adds a new Tab.
   * @param tab
   */
  def +=(tab: Tab) =
   if(tab.icon.isDefined) {
     if(tab.toolTip.isDefined)
       tabs.addTab(tab.title, tab.icon.get,tab.component.wrapped,
                   tab.toolTip.get)
     else tabs.addTab(tab.title, tab.icon.get, tab.component.wrapped)

   } else {
     if(tab.toolTip.isDefined)
        tabs.addTab(tab.title, null, tab.component.wrapped, tab.toolTip.get)
     else tabs.addTab(tab.title, tab.component.wrapped)
   }

  /**
    * Removes a Tab by its Component
    * @param comp
    */
  def -=(comp: Component) = tabs.remove(comp.wrapped)

  /**
   * Adds a sequence of tabs
   * @param ts
   */
  def ++=(ts: Tab*) = for(t <- ts) this.+=(t)

  /**
   * Removes a sequence of tabs by its components
   * @param components
   */
  def --=(components: Component*) =
    for(comp <- components) tabs.remove(comp.wrapped)

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() = tabs.getComponents

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    tabs.getComponentCount

  /**
   * @param ix
   * @return with the icon at the given index's tab
   */
  def iconAt(ix: Int) = tabs.getIconAt(ix)

  /**
   * Sets the icon on the given index's tab.
   * @param ix
   * @param icon
   */
  def iconAt(ix: Int, icon: Icon) = tabs.setIconAt(ix, icon)

  /**
   * @param ix
   * @return with the given index's component
   */
  def componentAt(ix: Int) = tabs.getComponentAt(ix)

  /**
   * Sets the component at the given index.
   * @param ix
   * @param comp
   */
  def componentAt(ix: Int, comp: Component) =
    tabs.setComponentAt(ix, comp.wrapped)

  /**
   * @return with the number of tabs.
   */
  def tabCount() = tabs.getTabCount

  /**
   * @return with the index of the selected tab.
   */
  def selectedIndex() = tabs.getSelectedIndex

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = tabs

  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer = tabs

  /**
   * @return with the name of the class
   */
  def className = "TabbedPane"
}