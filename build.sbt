name := "suit"

version := "0.8.5"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.jfree" % "jfreechart" % "1.0.14",
  "org.scala-lang" % "scala-reflect" % "2.11.6"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint"
)