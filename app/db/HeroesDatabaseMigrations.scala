package db

import com.google.inject.Inject
import play.api.Configuration

case class HeroesDatabaseMigrations @Inject()(config: Configuration) extends DatabaseMigrations {
  val host: String = config.get[String]("database.properties.serverName")
  val port: String = config.get[String]("database.properties.portNumber")
  val database: String = config.get[String]("database.properties.databaseName")
  val user: String = config.get[String]("database.properties.user")
  val password: String = config.get[String]("database.properties.password")
}
