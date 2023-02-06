package db.table

import model.Hero
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape


class HeroTable(tag: Tag) extends Table[Hero](tag, "hero_data") {

  def id: Rep[Option[Int]] = column[Option[Int]]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  override def * : ProvenShape[Hero] = {
    (
      id,
      name
    )
  } <> ((Hero.apply _).tupled, Hero.unapply)
}

object HeroTable {
  lazy val heroesTable = TableQuery[HeroTable]
}
