/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

/**
 * @author Steven Dobay
 */
case class FileChooser(private val initSelected: Array[File]
                         = Array.fill(0)(null))
  extends Component {
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

  def wrapped = chooser
  def className = "FileChooser"
}

object FileChooser {
  def apply(file: File) = new FileChooser(Array(file))
}
