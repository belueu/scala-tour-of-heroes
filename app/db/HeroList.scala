package db

import model.Hero
import play.api.libs.json.{ Json, OFormat }

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class HeroList(heroList: ListBuffer[Hero])

object HeroList {
  val heroes = new mutable.ListBuffer[Hero]()

  heroes += Hero(id = Some(12), name = "Dr. Nice")
  heroes += Hero(id = Some(13), name = "Bombasto")
  heroes += Hero(id = Some(14), name = "Celeritas")
  heroes += Hero(id = Some(15), name = "Magneta")
  heroes += Hero(id = Some(16), name = "RubberMan")
  heroes += Hero(id = Some(17), name = "Dynama")
  heroes += Hero(id = Some(18), name = "Dr. IQ")
  heroes += Hero(id = Some(19), name = "Magma")
  heroes += Hero(id = Some(20), name = "Tornado")

  implicit val listFormat: OFormat[HeroList] = Json.format[HeroList]
}

