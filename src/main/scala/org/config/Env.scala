package org.config

import com.typesafe.config.{Config, ConfigFactory}

object Env {
  private val config: Config = ConfigFactory.load()

  object MongoConfig {
    private val mongoConfig = "applicationConfig.mongoConfig."

    def getValue(key: String) = config.getString(mongoConfig + key)
  }
}
