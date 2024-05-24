package bikeinfo

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.raquo.laminar.api.L.{*, given}

import org.scalajs.dom
import bikeinfo.SearchPanel.selectedBikeStationBus
import bikeinfo.SearchPanel.selectedBikeStationRx

// import javascriptLogo from "/javascript.svg"
@js.native @JSImport("/javascript.svg", JSImport.Default)
val javascriptLogo: String = js.native

@main
def LiveChart(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Main.appElement()
  )

enum View:
  case SearchPage()
  case MapPage(bikeStation: BikeStation)

enum LeafMap:
  case Show
  case Hide

object Main:

  def bikeTable() = BTable.render()

  val showMapObs = Observer[dom.MouseEvent](
    onNext = ev => {
      val leafMap: js.Any = js.Dynamic.global.leafletApi.showMap()
      dom.console.log(ev.screenX)
    }
  )

  val hideMapObs = Observer[dom.MouseEvent](
    onNext = ev => {
      val leafMap: js.Any = js.Dynamic.global.leafletApi.hideMap()
      dom.console.log("hiding map")
    }
  )

  val bikeStationObserver: Observer[Option[BikeStation]] =
    Observer[Option[BikeStation]] {
      case Some(station) => js.Dynamic.global.leafletApi.showMap()
      case None => // do nothing
    }

  def appElement(): Element =
    div(
      h1("Bikes Available"),
      div(SearchPanel.searchField()),
      bikeTable(),
      child <-- SearchPanel.selectedBikeStationRx.map {
        case Some(bikeStation) =>
          scala.scalajs.js.Dynamic.global.leafletApi.showMap(
            bikeStation.lat,
            bikeStation.lon,
            bikeStation.name
          )
          div(
            button(
              "CLOSE MAP",
              onClick.mapTo(None) --> selectedBikeStationBus.writer
            )
          )
        case None =>
          js.Dynamic.global.leafletApi.hideMap()
          div(
            "search for a bike station:",
            div("type: jen"),
            div("hit <ENTER>")
          )
      }
    )

  end appElement

end Main

