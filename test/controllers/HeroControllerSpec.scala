package controllers

import controller.HeroController
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HeroControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HeroController GET from / path" should {

    "get the list of heroes from a new instance of controller" in {
      val controller = new HeroController(stubControllerComponents())
      val home = controller.getHeroes.apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include ("Magneta")
    }

    "get the list of heroes from the application" in {
      val controller = inject[HeroController]
      val home = controller.getHeroes().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include ("Bombasto")
    }

    "get the list of heroes from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsString(home) must include ("Dr. Nice")
    }
  }

//  "HeroController GET from /api/heroes path" should {
//
//    "get the list of heroes from a new instance of controller" in {
//      val controller = new HeroController(stubControllerComponents())
//      val home = controller.getHeroes.apply(FakeRequest(GET, "/api/heroes"))
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("application/json")
//      contentAsString(home) must include("Magneta")
//    }
//
//    "get the list of heroes from the application" in {
//      val controller = inject[HeroController]
//      val home = controller.getHeroes().apply(FakeRequest(GET, "/api/heroes"))
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("application/json")
//      contentAsString(home) must include("Bombasto")
//    }
//
//    "get the list of heroes from the router" in {
//      val request = FakeRequest(GET, "/api/heroes")
//      val home = route(app, request).get
//
//      status(home) mustBe OK
//      contentType(home) mustBe Some("application/json")
//      contentAsString(home) must include("Dr. Nice")
//    }
//  }
}
