package simulation

import BasePerf.baseSimulation
import Features.mock.MockUtils

import scala.language.postfixOps
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._

class CatsSimulation extends baseSimulation {

  MockUtils.startServer()

  val feeder = Iterator.continually(Map("catName" -> MockUtils.getNextCatName))

  val protocol = karateProtocol(
    "/cats/{id}" -> Nil,
    "/cats" -> pauseFor("get" -> 15, "post" -> 25)
  )

 // protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")

  val create = scenario("create").feed(feeder).exec(karateFeature("classpath:Features/mock/cats-create.feature"))
  val delete = scenario("delete").group("delete cats") {
    exec(karateFeature("classpath:Features/mock/cats-delete.feature@name=delete"))
  }
  val custom = scenario("custom").exec(karateFeature("classpath:Features/mock/custom-rpc.feature"))

  setUp(
    create.inject(rampUsers(10) during (5 seconds)).protocols(protocol),
    delete.inject(rampUsers(5) during (5 seconds)).protocols(protocol),
    custom.inject(rampUsers(10) during (5 seconds)).protocols(protocol)
  )

}
