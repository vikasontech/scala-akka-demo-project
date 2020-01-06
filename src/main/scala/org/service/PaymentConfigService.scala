package org.service

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import org.docs.PaymentConfigDoc
import org.mongodb.scala.Completed
import org.repositories.PaymentConfigRepo

import scala.concurrent.Future

class PaymentConfigService(implicit val system: ActorSystem) {

  def insertPaymentConfigData(paymentConfigDoc: PaymentConfigDoc): Future[Completed] = {
    PaymentConfigRepo.insertData(paymentConfigDoc)
  }

  def getData: Source[PaymentConfigDoc, NotUsed] = {
    Source.fromFuture(PaymentConfigRepo.getData)
      .mapConcat{
        identity
      }
  }
}