package org.database

import ch.rasc.bsoncodec.math.BigDecimalStringCodec
import com.mongodb.MongoCredential
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.jsr310.LocalDateTimeCodec
import org.config.Env
import org.docs.PaymentConfigDoc
import org.mongodb.scala.{Document, MongoClientSettings, MongoCollection, ServerAddress}

import scala.collection.JavaConverters.seqAsJavaListConverter


object DbConfig {
  private val config: Env.MongoConfig.type = Env.MongoConfig

  private val credential: MongoCredential = MongoCredential.createCredential(
    config.getValue("user"), config.getValue("source"),
    config.getValue("password").toCharArray)


  import org.bson.codecs.configuration.CodecRegistries
  import org.bson.codecs.configuration.CodecRegistries._
  import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
  import org.mongodb.scala.bson.codecs.Macros._
  import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

  private val javaCodeRegistry: CodecRegistry = CodecRegistries.fromCodecs(
    new LocalDateTimeCodec(),
    new BigDecimalStringCodec()
  )

  private val providerRegistry: CodecRegistry = CodecRegistries.fromProviders(classOf[PaymentConfigDoc])

  private val settings: MongoClientSettings = MongoClientSettings.builder()
    .applyToClusterSettings(b =>
      b.hosts(List(new ServerAddress(config.getValue("url"))).asJava))
    .credential(credential)
    .codecRegistry(fromRegistries(javaCodeRegistry, providerRegistry, DEFAULT_CODEC_REGISTRY))
    .build()

  private val client: MongoClient = MongoClient(settings)

  val database: MongoDatabase = client.getDatabase(config.getValue("database"))
}
