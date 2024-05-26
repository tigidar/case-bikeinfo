package bikeinfo.model

final case class BikeStation(
  stationId: Int,
  lat: Double,
  lon: Double,
  name: String,
  address: String,
  description: String,
  availableDocks: Int,
  availableBikes: Int
)

object BikeStation:
  implicit def bikeStationRW: upickle.default.ReadWriter[BikeStation] =
      upickle.default.macroRW[BikeStation]

import com.raquo.laminar.api.L.{*, given}

