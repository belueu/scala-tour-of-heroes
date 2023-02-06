package util

import org.slf4j.{ Logger, LoggerFactory }

trait Logging {

  implicit lazy val log: Logger = LoggerFactory.getLogger(this.getClass)
}
