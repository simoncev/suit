/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test.layout

import org.suit.layouts.GroupLayout
import org.suit.{Button, Size, DesktopApp}

/**
 * @author Steven Dobay
 */
object GroupLayoutExample extends DesktopApp("GroupLayout!!!") {

  frame.size = Size(800, 600)

  /**
   * Adding the layout:
   */
  val layout = new GroupLayout(frame)

  // The following example compiled from java's example:
  // https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html

  layout.autoCreateGaps = true
  layout.autoCreateContainerGaps = true

  layout.horizontalGroup += Button("1")
  layout.horizontalGroup += Button("2")
   val group1 = layout.createGroup(GroupLayout.LEADING)
   group1 += Button("3")
   group1 += Button("4")
  layout.horizontalGroup ++= group1
  layout.horizontalGroup.build() // DON'T FORGET TO BUILD!

  val group2 = layout.createGroup(GroupLayout.BASELINE)
  group2 += Button("5")
  group2 += Button("6")
  group2 += Button("7")
  layout.verticalGroup ++= group2
  layout.verticalGroup += Button("8")
  layout.verticalGroup.build() // DON'T FORGET TO BUILD!

  /* Compared to java:
GroupLayout layout = new GroupLayout(panel);
 panel.setLayout(layout);

We specify automatic gap insertion:

layout.setAutoCreateGaps(true);
layout.setAutoCreateContainerGaps(true);

Then, we define the groups and add the components. We establish a root group for each dimension using the setHorizontalGroup and setVerticalGroup methods. Groups are created via createSequentialGroup and createParallelGroup methods. Components are added to groups by using the addComponent method.

layout.setHorizontalGroup(
   layout.createSequentialGroup()
      .addComponent(c1)
      .addComponent(c2)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(c3)
           .addComponent(c4))
);
layout.setVerticalGroup(
   layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
           .addComponent(c1)
           .addComponent(c2)
           .addComponent(c3))
      .addComponent(c4)
);

 */
}
