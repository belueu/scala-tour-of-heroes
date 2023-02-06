package db

import org.flywaydb.core.Flyway
import util.Logging

trait DatabaseMigrations extends Logging {
  val host: String
  val port: String
  val database: String
  val user: String
  val password: String

  private lazy val flyway = Flyway.configure
    .dataSource(buildDatabaseUrl(), user, password)
    .validateOnMigrate(true)

  def run(): Unit = {
    log.info("Running migrations...")
    flyway.load().migrate()
  }

  private def buildDatabaseUrl(): String = s"jdbc:postgresql://$host:$port/$database"
}
