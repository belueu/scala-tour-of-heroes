import mill._
import $ivy.`com.lihaoyi::mill-contrib-playlib:`,  mill.playlib._

object scalatourofheroes extends PlayModule with SingleModule {

  def scalaVersion = "2.12.16"
  def playVersion = "2.8.18"
  def twirlVersion = "1.5.1"

  object test extends PlayTests
}
