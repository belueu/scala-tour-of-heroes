package module

import akka.actor.ActorSystem
import akka.stream.{ Materializer, SystemMaterializer }
import dao.HeroDao
import net.codingwell.scalaguice.ScalaModule
import service.HeroService
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class DependencyModule extends ScalaModule {

  val system: ActorSystem = ActorSystem()
  val ec: ExecutionContext = system.dispatcher

  lazy val db: Database = {
    val database = Database.forConfig("database")
    system.registerOnTermination(database.close())
    database
  }

  val heroDao: HeroDao = new HeroDao(db)
  val heroService: HeroService = new HeroService(heroDao, ec)

  override def configure(): Unit = {
    bind(classOf[ActorSystem]).toInstance(system)
    bind(classOf[Materializer]).toInstance(SystemMaterializer(system).materializer)
    bind(classOf[ExecutionContext]).toInstance(system.dispatcher)
    bind(classOf[Database]).toInstance(db)
    bind(classOf[HeroDao]).toInstance(heroDao)
    bind(classOf[HeroService]).toInstance(heroService)
  }
}
