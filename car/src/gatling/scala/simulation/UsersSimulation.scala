package simulation

import BasePerf.baseSimulation
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import pageObjects.Users.getUsers;


class UsersSimulation extends baseSimulation {

  val protocol = karateProtocol(
    "/users" -> Nil
  )

  override def userCount:Int = getProperty("USERS","5").toInt
  override def userCountPerSec:Int = getProperty("USERS_PER_SEC","5").toInt
  override def rampDuration :Int = getProperty("RAMP_DURATION","15").toInt
  override def testDuration:Int = getProperty("DURATION","10").toInt
  override def pauseDuration:Int = getProperty("PAUSE_DURATION","2").toInt

  setUp(
    getUsers.inject(
      nothingFor(pauseDuration.seconds),
      atOnceUsers(userCount),
      rampUsers(rampDuration).during(testDuration.seconds),
      constantUsersPerSec(userCount).during(rampDuration.seconds),
      rampUsersPerSec(userCount).to(testDuration).during(testDuration.seconds)
    )
  ).protocols(protocol)
   .assertions(
     global.responseTime.percentile3.lt(1000),   // 95th percentile < 1000ms
     global.successfulRequests.percent.gt(95)    // > 95% of requests must succeed
   )
}
