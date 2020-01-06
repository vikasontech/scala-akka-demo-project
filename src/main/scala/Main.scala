import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern.Patterns
import org.actors.PaymentConfigActor
import org.actors.PaymentConfigActor.FindAll
import org.docs.PaymentConfigDoc

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

object Main extends App {

  private val hello: ActorSystem = ActorSystem("hello")
  private val ref: ActorRef = hello.actorOf(Props[PaymentConfigActor])
  private val future: Future[AnyRef] = Patterns.ask(ref, FindAll, 2 seconds)
  private val value: Seq[PaymentConfigDoc] = Await.result(future, Duration.Inf).asInstanceOf[Seq[PaymentConfigDoc]]
  println(value)
}
