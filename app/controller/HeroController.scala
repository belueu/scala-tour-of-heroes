package controller

import model.Hero
import play.api.mvc._
import service.HeroService
import util.Logging

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.reflectiveCalls

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HeroController @Inject()(
  val heroService: HeroService,
  override val controllerComponents: ControllerComponents
) extends AbstractController(controllerComponents) with Logging {

  def getHeroes: Action[AnyContent] = Action.async { _ =>
    heroService.getAllHeroes
  }

  def getHero(id: Int): Action[AnyContent] = Action.async { _ =>
    heroService.getHeroById(id)
  }


  def findHero(name: String): Action[AnyContent] = Action.async { _ =>
    heroService.searchHero(name)
  }

  def addHero(): Action[AnyContent] = Action.async { request =>
    request.body.asJson.get.validate[Hero].asOpt.fold {
      Future(BadRequest("No hero added"))
    } {
      response => {
        heroService.addHero(response)
      }
    }
  }

  def updateHero(): Action[AnyContent] = Action.async { request =>
    request.body.asJson.get.validate[Hero].asOpt.fold {
      Future(BadRequest("No hero updated"))
    } {
      response => {
        heroService.updateHero(response)
      }
    }
  }

  def deleteHero(id: Int): Action[AnyContent] = Action.async { _ =>
    heroService.deleteHero(id)
  }
}
