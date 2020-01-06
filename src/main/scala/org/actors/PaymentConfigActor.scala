package org.actors

import akka.actor.{Actor, ActorLogging, ActorSystem}
import org.docs.PaymentConfigDoc
import org.service.PaymentConfigService

class PaymentConfigActor extends Actor with ActorLogging {
  import PaymentConfigActor._

  implicit val system: ActorSystem = context.system

  private val service = new PaymentConfigService()
  override def receive: Receive = {

    case InsertData(paymentConfigDoc) =>
      sender ! service.insertPaymentConfigData(paymentConfigDoc)

    case FindAll =>
      sender ! service.getData
  }
}

object PaymentConfigActor {

  case class InsertData(paymentConfigDoc: PaymentConfigDoc)

  object FindAll

}