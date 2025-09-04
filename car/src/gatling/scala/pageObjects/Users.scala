package pageObjects

import com.intuit.karate.gatling.PreDef.karateFeature
import io.gatling.core.Predef.scenario

object Users {
  val getUsers = scenario("Get Users Performance Test")
    .exec(karateFeature("classpath:Features/users/users.feature"))
}
