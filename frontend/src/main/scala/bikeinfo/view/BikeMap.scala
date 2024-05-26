package bikeinfo.view

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport}
import bikeinfo.data.DataExample
import bikeinfo.state.ViewState
import bikeinfo.model.*

@JSImport("/bikemap/Bikemap.js", JSImport.Default)
@js.native
class BikemapJs(
  lat: Double,
  lon: Double,
  zoomLevel: Int,
  clickAction: js.Function2[Double, Double, Unit]
) extends js.Object {

  def showStation(bikeStation: js.Object): Unit =
    js.native

  def showAll(stations: js.Array[js.Object]): Unit = js.native

  def hideMap(): Unit = js.native
  
  def clearMap(): Unit = js.native

}

final class Bikemap(
  map: BikemapJs
):
  
  def showStation(bikeStation: BikeStation): Unit =
    ViewState.showMap()
    val bikeJs = upickle.default.write(bikeStation)
    // a bit inefficient, but saving a frew keystrokes
    map.showStation(js.JSON.parse(bikeJs).asInstanceOf[js.Object])

  def showAll(stations: js.Array[js.Object]): Unit =
    ViewState.showMap()
    map.showAll(stations)

  def hideMap(): Unit =
    ViewState.hideMap()
    map.hideMap()
  
  def clearMap(): Unit =
    map.clearMap()

object BikemapRef:

  type Coordinate = (Double, Double)

  private def squaredDistance(p1: Coordinate, p2: Coordinate): Double = {
    val (lat1, lon1) = p1
    val (lat2, lon2) = p2
    val dLat = lat1 - lat2
    val dLon = lon1 - lon2
    dLat * dLat + dLon * dLon
  }

  import scala.scalajs.js.JSConverters._
  private def findClosestStations(reference: Coordinate): 
    js.Array[js.Object] = 
    DataExample.bikes.map { station =>
      val lat = station.lat
      val lon = station.lon
      (station, squaredDistance((lat,lon),reference))
    }.sortBy(_._2)
    .take(3)
    .map(_._1)
    .map { bs =>
      val bikeJs = upickle.default.write(bs)
      js.JSON.parse(bikeJs).asInstanceOf[js.Object]
    }
    .toJSArray

  val clickAction: js.Function2[Double, Double, Unit] = 
    (lat: Double, lng: Double) => {
      val stations = findClosestStations((lat,lng))
      bikeMap.showAll(stations)
  }

  lazy val bikeMap = Bikemap(BikemapJs(
      59.9139, 10.7522, 13, clickAction
    )
  )

