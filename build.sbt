scalaVersion := "2.13.14"
organization := "udemy.anish"

lazy val hello = (project in file(".")).settings(name := "Manage_Expense")

addCompilerPlugin(
  "org.typelevel" %% "kind-projector" % "0.13.3" cross CrossVersion.full
)

libraryDependencies += "org.typelevel" %% "cats-core" % "2.12.0"
libraryDependencies += "org.typelevel" %% "cats-laws" % "2.11.0"
libraryDependencies += "org.typelevel" %% "discipline-core" % "1.7.0"
libraryDependencies += "org.typelevel" %% "discipline-scalatest" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19"
