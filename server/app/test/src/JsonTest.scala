import utest._

import ujson.*

object JsonTest extends TestSuite {

  val jsonPath = 
    os.pwd / "app" / "test" / "resources"

  val stasjonStatus = jsonPath / "stasjon_status.json"
  val stasjonInfo = jsonPath / "stasjoner.json"

  val tests = Tests {
    test("create BikeStation from json"){
      val statusJson = os.read(stasjonStatus)
      val stationJson = os.read(stasjonInfo)
      val bikeStations = bikeinfo.JsonParser(statusJson, stationJson)
      bikeStations.length == 256
    }
  }
}

