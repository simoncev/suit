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
 *
 * Simple dialog to choose files.
 */
case class FileChooser(private val initSelected: Array[File]
                         = Array.fill(0)(null))
  extends Bindable[Array[File]] {

  private val chooser = new JFileChooser()

  chooser.putClientProperty("suit-wrapper", this)

  if(!initSelected.isEmpty) chooser.setSelectedFiles(initSelected)

  /**
   * Runs the file chooser dialog.
   * @param parent : parent container
   * @return with true if a there was a file selection.
   */
  def runOpen(parent: Container) =
    chooser.showOpenDialog(parent.wrappedContainer) match {
      case JFileChooser.APPROVE_OPTION => true
      case _ => false
    }

  /**
   * Runs a save dialog.
   * @param parent
   * @return with true if a there was a file selection.
   */
  def runSave(parent: Container) =
    chooser.showSaveDialog(parent.wrappedContainer) match {
    case JFileChooser.APPROVE_OPTION => true
    case _ => false
  }

  /**
   * Runs custom file chooser dialog.
   * @param parent
   * @param title
   * @return with true if a there was a file selection.
   */
  def run(parent: Container, title: String) =
    chooser.showDialog(parent.wrappedContainer, title) match {
      case JFileChooser.APPROVE_OPTION => true
      case _ => false
    }

  /**
   * @return with the slected file
   */
  def selectedFile() = chooser.getSelectedFile

  /**
   * @return with the selected files
   */
  def selectedFiles() = chooser.getSelectedFiles

  /**
   * Sets the default selected file.
   * @param file
   */
  def selectedFile_=(file: File) = chooser.setSelectedFile(file)

  /**
   * Sets the default selected files.
   * @param files
   */
  def selectedFiles_=(files: Array[File]) =
    chooser.setSelectedFiles(files)

  /**
   * Sets the default selected files.
   * @param files
   * @return with self.
   */
  def withSelectedFiles(files: Array[File]) = {
    selectedFiles_=(files)
    this
  }

  /**
   * Cancels the selection.
   */
  def cancel() = chooser.cancelSelection()

  /**
   * Checks file visibility.
   * @param file
   */
  def ensureFileIsVisible(file: File) =
    chooser.ensureFileIsVisible(file)

  /**
   * Adds a new filter.
   * @param pred
   * @param goalDescription
   */
  def addFilter(pred: File => Boolean, goalDescription: String = "") =
     chooser.setFileFilter(new FileFilter {
       override def getDescription: String = goalDescription
       override def accept(f: File): Boolean = pred(f)
     })

  /**
   * Adds a new filter
   * @param pred
   * @param goalDescription
   * @return with self
   */
  def withFilter(pred: File => Boolean, goalDescription: String = "") = {
    addFilter(pred, goalDescription)
    this
  }

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = chooser

  /**
   * @return with the name of the class
   */
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

  def componentValue() = selectedFiles()
}

object FileChooser {
  /**
   * Creates a file-chooser with one default file.
   */
  def apply(file: File) = new FileChooser(Array(file))
}
