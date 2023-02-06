package dao

import com.google.inject.Inject
import db.HeroList.heroes
import db.table.HeroTable
import db.{ DatabaseConnector, HeroList }
import model.Hero
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future

class HeroDao @Inject()(
  val db: Database
) extends DatabaseConnector {

  /**
   * Methods that access the real db
   * */

  def retrieveHeroesDao: Future[Seq[Hero]] = runAction {
    HeroTable.heroesTable.result
  }

  /**
   * Methods that access the mock db object `HeroList`
   * */

  def retrieveHeroes: ListBuffer[Hero] = HeroList.heroes
  def retrieveHeroById(id: Int): ListBuffer[Hero] = HeroList.heroes.filter(_.id.get == id)
  def searchHeroByTerm(term: String): ListBuffer[Hero] = HeroList.heroes.filter(_.name.contains(term))
  def insertHero(hero: Hero): Hero = {
    HeroList.heroes += hero
    hero
  }
  def removeHeroById(id: Int): heroes.type = {
    val foundHero: Hero = HeroList.heroes.filter(_.id.get == id).head
    HeroList.heroes -= foundHero
  }
  def updateHero(hero: Hero): ListBuffer[Hero] = {
    val foundHero = HeroList.heroes.filter(_.id.get == hero.id.get).head
    val index = HeroList.heroes.indexOf(foundHero)
    HeroList.heroes.update(index, hero)
    HeroList.heroes
  }
}
