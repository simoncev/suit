name := "suit"

version := "0.8.8"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.jfree" % "jfreechart" % "1.0.14",
  "org.scala-lang" % "scala-reflect" % "2.11.6",
  "net.java.dev.jna" % "jna" % "4.1.0",
  "net.java.dev.jna" % "jna-platform" % "4.1.0"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint"
)