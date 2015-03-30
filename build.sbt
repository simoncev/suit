name := "scala-frame"

version := "0.1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq (
 "org.scalatest" %% "scalatest" % "2.2.4" % "test",
 "org.jfree" % "jfreechart" % "1.0.14"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint"
)