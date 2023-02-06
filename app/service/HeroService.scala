package service

import com.google.inject.Inject
import dao.HeroDao
import db.HeroList
import model.{ Hero, Heroes }
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results.{ Created, Ok }

import scala.concurrent.{ ExecutionContext, Future }

class HeroService @Inject()(
  val heroDao: HeroDao,
  implicit val ec: ExecutionContext
) {

  def getAllHeroes: Future[Result] = {
    for {
      heroSeq <- heroDao.retrieveHeroesDao
    } yield Ok(Json.toJson(Heroes(heroSeq)))
  }

  def getHeroById(id: Int): Future[Result] = {
    Future(Ok(Json.toJson(heroDao.retrieveHeroById(id).head)))
  }

  def searchHero(term: String): Future[Result] = {
    Future(Ok(Json.toJson(heroDao.searchHeroByTerm(term))))
  }

  def addHero(hero: Hero): Future[Result] = {
    val newHero = Hero(Some(getNextId), hero.name)
    Future(Created(Json.toJson(heroDao.insertHero(newHero))))
  }

  def updateHero(hero: Hero): Future[Result] = {
    Future(Ok(Json.toJson(heroDao.updateHero(hero))))
  }

  def deleteHero(id: Int): Future[Result] = {
    Future(Ok(Json.toJson(heroDao.removeHeroById(id))))
  }

  private def getNextId: Int = {
    if (HeroList.heroes.nonEmpty) {
      heroDao.retrieveHeroes.map(_.id.get).max + 1
    } else 10
  }
}
