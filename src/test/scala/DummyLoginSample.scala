/**
 * Copyright© by Steven Dobay on 2015
 */
package scala.test

import org.suit._
import org.suit.layouts.BorderLayout

/**
 * @author Steven Dobay
 */
object DummyLoginSample extends DesktopApp("Login Sample") {
  frame.size = Size(300, 300)

  var (userName, password) = ("user", "pass123")

  val loginPanel = Panel()
  loginPanel.layout = layouts.BorderLayout()

  loginPanel.add(Label("Sign in!"), BorderLayout.NORTH)

  val innerPanel = Panel()
  loginPanel.add(innerPanel, BorderLayout.CENTER)

  innerPanel.layout = layouts.GridLayout(2, 2)

  innerPanel += Label("Username: ")
  val uname = TextField()
  innerPanel += uname

  innerPanel += Label("Password: ")
  val pass = PasswordField()
  innerPanel += pass

  def validate() =
   if(userName == uname.text && password == pass.text)
     popups.info("Success!", "Successful login as " + userName)
   else
     popups.error("Error!", "Bad login!")

  loginPanel.add(
    Button("Submit") @> validate(),
    BorderLayout.SOUTH
  )

  frame += loginPanel
}