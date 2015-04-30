name := "suit"

version := "0.10.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "org.jfree" % "jfreechart" % "1.0.14",
  "org.scala-lang" % "scala-reflect" % "2.11.6",
  "net.java.dev.jna" % "jna" % "4.1.0",
  "net.java.dev.jna" % "jna-platform" % "4.1.0",
  "com.miglayout" % "miglayout-swing" % "5.0"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint"
)