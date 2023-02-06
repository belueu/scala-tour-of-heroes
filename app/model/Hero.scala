package model

import play.api.libs.json.{ Json, OFormat }

case class Hero(
  id: Option[Int],
  name: String
)

object Hero {
  implicit val formatHero: OFormat[Hero] = Json.format[Hero]
}

case class Heroes(
  heroes: Seq[Hero]
)

object Heroes {
  implicit val formatHeroSeq: OFormat[Heroes] = Json.format[Heroes]
}