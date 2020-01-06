package org.repositories

import akka.NotUsed
import akka.actor.{Actor, ActorLogging, ActorSystem}
import akka.stream.scaladsl.Source
import org.config.json.JsonUtils
import org.database.MongoCollectionConfig
import org.docs.PaymentConfigDoc
import org.mongodb.scala.{Completed, Document, FindObservable, MongoCollection}

import scala.concurrent.Future

object PaymentConfigRepo extends JsonUtils {
   val paymentConfigDocs: MongoCollection[PaymentConfigDoc] = MongoCollectionConfig
    .paymentConfigDocs

  def insertData(config: PaymentConfigDoc): Future[Completed] = {
    paymentConfigDocs.insertOne(config).toFuture()
  }

  def getData: Future[Seq[PaymentConfigDoc]] = {
    paymentConfigDocs.find().toFuture()
  }
}



