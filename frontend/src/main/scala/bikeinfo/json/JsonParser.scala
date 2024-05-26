package bikeinfo.json

import ujson.*
import scala.util.Try
import scala.collection.mutable.ArrayBuffer
import bikeinfo.model.*

object JsonParser:

  private def convertToMap(data: String): Map[String, ArrayBuffer[Value]] =
    val jsonArray = ujson.read(data)("data")("stations").arr
    jsonArray.groupBy[String] { obj =>
      val o = obj.obj
      o("station_id").str
    }

  private def bikeStationFactory(
    statusArr: ArrayBuffer[Value],
    stationArr: ArrayBuffer[Value]
  ): Either[Throwable, BikeStation] = 
  Try {
    val status = statusArr.head.obj
    val station = stationArr.head.obj
    BikeStation(
      status("station_id").str.toInt,
      station("lat").num,
      station("lon").num,
      station("name").str,
      station("address").str,
      "",
      //station("description").str,
      status("num_bikes_available").num.toInt,
      status("num_docks_available").num.toInt,
    ) 
  }.toEither

  def apply(
    statusData: String, stationData: String
  ): Vector[BikeStation] =
    val statusMap = convertToMap(statusData)
    val stationsMap = convertToMap(stationData)
    statusMap.foldLeft(Vector.empty[BikeStation]) {
      case (acc,(key, status)) =>
        val station: Option[ArrayBuffer[Value]] = stationsMap.get(key)
        station.fold(acc) { station =>
          bikeStationFactory(status, station) match {
            case Right(bs) => 
              acc :+  bs
            case Left(t) =>
              println(t)
              acc
          }
        }
    }
  
