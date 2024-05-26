package bikeinfo.state

import com.raquo.laminar.api.L.{*, given}
import bikeinfo.model.*
import bikeinfo.data.*

object ViewState:

  lazy val selectedBikeStationBus: EventBus[Option[BikeStation]] = new EventBus
  lazy val selectedBikeStationRx: Signal[Option[BikeStation]] = 
    selectedBikeStationBus.events.toSignal(None)

  lazy val searchTextBus: EventBus[String] = new EventBus
  lazy val searchTextSignal: Signal[Vector[BikeStation]] =
    stationSuggestions.toSignal(Vector.empty)

  def stationFilter(searchText: String, bikeStation: BikeStation): Boolean = 
    val text = searchText.trim.toLowerCase()
    val name = bikeStation.name.toLowerCase()
    val address = bikeStation.address.toLowerCase()
    name.contains(text) || address.contains(text)

  lazy val stationSuggestions: EventStream[Vector[BikeStation]] = 
    searchTextBus.events.map { text =>
      if text.trim.length < 2 then Vector.empty
      else 
        DataExample.bikes
          .filter(b => stationFilter(text,b))
    }

  lazy val MapIsVisible = Var(true)

  def hideMap() =
    MapIsVisible.update(_ => false)

  def showMap() =
    MapIsVisible.update(_ => true)

