package db

import slick.dbio.Effect.All
import slick.dbio.{ DBIOAction, NoStream }
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.Future

trait DatabaseConnector {
  val db: Database

  def runAction[T](action: DBIOAction[T, NoStream, All]): Future[T] = {
    db.run(action)
  }
}
