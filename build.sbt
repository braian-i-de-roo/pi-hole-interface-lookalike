enablePlugins(ScalaJSPlugin)

name := "pihole-interface-lookalike"

version := "0.1"

scalaVersion := "2.13.5"

idePackagePrefix := Some("com.braian")

scalaJSUseMainModuleInitializer := true

libraryDependencies += "com.raquo" %%% "laminar" % "0.12.2"
