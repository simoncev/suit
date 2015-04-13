/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

/**
 * @author Steven Dobay
 */
case class FileChooser(private val initSelected: Array[File]
                         = Array.fill(0)(null))
  extends Bindable[Array[File]] {

  private val chooser = new JFileChooser()

  if(!initSelected.isEmpty) chooser.setSelectedFiles(initSelected)

  def selectedFile() = chooser.getSelectedFile
  def selectedFiles() = chooser.getSelectedFiles

  def selectedFile_=(file: File) = chooser.setSelectedFile(file)
  def withSelectedFile(file: File) =
    selectedFile_=(file)

  def selectedFiles_=(files: Array[File]) =
    chooser.setSelectedFiles(files)

  def withSelectedFiles(files: Array[File]) = {
    selectedFiles_=(files)
    this
  }

  def cancel() = chooser.cancelSelection()

  def ensureFileIsVisible(file: File) =
    chooser.ensureFileIsVisible(file)

  def addFilter(pred: File => Boolean, goalDescription: String = "") =
     chooser.setFileFilter(new FileFilter {
       override def getDescription: String = goalDescription
       override def accept(f: File): Boolean = pred(f)
     })

  def withFilter(pred: File => Boolean, goalDescription: String = "") = {
    addFilter(pred, goalDescription)
    this
  }

  protected[suit] def wrapped = chooser
  def className = "FileChooser"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ChangeEvent
  protected type ChangeListenerType = ActionListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def actionPerformed(e: java.awt.event.ActionEvent): Unit =
       proc(ChangeEvent(e))
    }
    chooser.addActionListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
   chooser.removeActionListener(l)

  def bindValue() = selectedFiles()
}

object FileChooser {
  def apply(file: File) = new FileChooser(Array(file))
}
