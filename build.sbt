name := """scala-tour-of-heroes"""
organization := "dev.belueu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.16"

libraryDependencies ++= Seq(
//  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "net.codingwell" %% "scala-guice" % "5.1.0",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "org.postgresql" % "postgresql" % "42.5.0",
  "org.flywaydb"            % "flyway-core"        % "7.0.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "dev.belueu.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "dev.belueu.binders._"
