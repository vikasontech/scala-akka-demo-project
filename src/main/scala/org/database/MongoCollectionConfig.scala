package org.database

import org.docs.PaymentConfigDoc
import org.mongodb.scala.{Document, MongoCollection}

object MongoCollectionConfig {
  private val database = DbConfig.database

  val paymentConfigDocs: MongoCollection[PaymentConfigDoc] = database.getCollection("paymentConfigDoc")
}

