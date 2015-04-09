/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{ActionEvent => JAction, ActionListener}
import javax.swing.JMenu

/**
 * @author Steven Dobay
 */
case class Menu(private val initTitle: String) {
  private var menu: JMenu = new JMenu(initTitle)

  /**
   * @return with the title of the menu
   */
  def title = menu.getText

  /**
   * Sets the menu's title
   * @param t
   */
  def title_=(t: String) =
    menu.setText(t)

  /**
   * Adds the item to the menu
   * @param item
   */
  def +=(item: MenuItem): Unit = menu.add(item.wrapped)

  /**
   * Removes the item from the menu
   * @param item
   */
  def -=(item: MenuItem): Unit = menu.remove(item.wrapped)

  /**
   * Adds the items to the menu
   * @param items
   * @return with the menu's pointer
   */
  def withItems(items: Array[MenuItem]): Menu = {
    for (item <- items) menu.add(item.wrapped)
    this
  }

  /**
   * Adds the items to the menu
   * @param items
   * @return with the menu's pointer
   */
  def withItems(items: MenuItem*): Menu =
    withItems(items.toArray)

  /**
   * Adds the items to the menu
   * @param items
   * @return with the menu's pointer
   */
  def ++=(items: MenuItem*): Menu =
    withItems(items.toArray)

  /**
   * Adds the action-handling to the menu
   * @param proc
   */
  def onAction_=(proc: ActionEvent => Unit) =
   menu.addActionListener(new ActionListener {
     override def actionPerformed(e: JAction): Unit =
       proc(ActionEvent(e))
   })

  /**
   * Adds the action-handling to the menu
   * @param proc
   * @return with the menu
   */
  def withAction(proc : ActionEvent => Unit): Menu = {
    menu.addActionListener(new ActionListener {
      override def actionPerformed(e: JAction): Unit =
        proc(ActionEvent(e))
    })
    this
  }

  /**
   * Adds the action-handling to the menu
   * @return with the menu
   */
  def @> = withAction _

  /**
   * @return with the wrapped object
   */
 def wrapped = menu

  /**
   * Updates the wrapped jmenu
   * @param m
   */
 def wrapped_=(m: JMenu) = this.menu = m

  /**
   * @param obj
   * @return true if the wrapped menu equals
   */
 override def equals(obj: Any) =
  if(obj.isInstanceOf[Menu])
    obj.asInstanceOf[Menu].wrapped == wrapped
  else if(obj.isInstanceOf[JMenu])
    obj.asInstanceOf[JMenu] == wrapped
  else false
}