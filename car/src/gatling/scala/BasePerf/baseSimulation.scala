package BasePerf

import io.gatling.core.Predef.Simulation

class baseSimulation extends Simulation {

   def userCount:Int = getProperty("USERS","5").toInt
   def userCountPerSec:Int = getProperty("USERS_PER_SEC","10").toInt
   def rampDuration :Int = getProperty("RAMP_DURATION","10").toInt
   def testDuration:Int = getProperty("DURATION","10").toInt
   def pauseDuration:Int = getProperty("PAUSE_DURATION","2").toInt

   before{
     println(s"Running test with ${userCount} users")
     println(s"Running test with ${userCountPerSec} users per second")
     println(s"Ramping users over ${rampDuration} seconds")
     println(s"Total test duration ${testDuration} seconds")
     println(s"pause duration ${pauseDuration} seconds")
   }
   after{
     println("load testing complete")
   }
   protected def getProperty(propName:String, defaultValue:String): String = {
      Option(System.getenv(propName)).orElse(
        Option(System.getProperty(propName))
      ).getOrElse(defaultValue)
   }
}
