name := "funplay"

organization := "com.hablapps"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

publishArtifact in (Compile, packageBin) := true

scalaSource in Compile := baseDirectory.value / "src"

scalacOptions += "-feature"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.1"
)
