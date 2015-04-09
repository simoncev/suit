/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.{Icon, JTabbedPane}

/**
 * @author Steven Dobay
 */
case class TabbedPane()
  extends Component with Container {

  private val tabs = new JTabbedPane()

  tabs.putClientProperty ("scala-frame-wrapper", this)

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

  def -=(comp: Component) = tabs.remove(comp.wrapped)

  def ++=(ts: Tab*) =
    for(t <- ts) this.+=(t)

  def --=(components: Component*) =
    for(comp <- components) tabs.remove(comp.wrapped)

  def iconAt(ix: Int) = tabs.getIconAt(ix)
  def iconAt(ix: Int, icon: Icon) = tabs.setIconAt(ix, icon)

  def componentAt(ix: Int) = tabs.getComponentAt(ix)

  def componentAt(ix: Int, comp: Component) =
    tabs.setComponentAt(ix, comp.wrapped)

  def tabCount() = tabs.getTabCount
  def selectedIndex() = tabs.getSelectedIndex

  def wrapped = tabs

  def className = "TabbedPane"
}