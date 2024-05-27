package bikeinfo

import scala.scalajs.js
import com.raquo.laminar.api.L.{*, given}

import org.scalajs.dom
import bikeinfo.state.ViewState
import bikeinfo.view.*
import bikeinfo.model.*

@main
def LiveChart(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Main.appElement()
  )

enum View:
  case SearchPage()
  case MapPage()

object Main:

  private def setupMapInteractivity() =
    ViewState.selectedBikeStationRx.foreach {
      case Some(bikeStation) =>
        BikemapRef.bikeMap.showStation(
          bikeStation
        )
      case None =>
        BikemapRef.bikeMap.hideMap()
    }(unsafeWindowOwner)

  val activeWindow: Var[View] = Var(View.MapPage())

  def searchButton = SearchPanel.baseButton(
    "Search",
    onClick.map(_ => View.SearchPage()) --> activeWindow
  )

  def findInMapButton = SearchPanel.baseButton(
    "Find in Map",
    onClick.map(_ => View.MapPage()) --> activeWindow
  )

  def switchWindow() = span(
    child <-- activeWindow.toObservable.map {
      case View.MapPage()    => searchButton
      case View.SearchPage() => findInMapButton
    }
  )

  def searchWindow = div(
    div(SearchPanel.searchField()),
    BikeStationTable.render()
  )

  def mapWindow = div(
    SelectInMap.render()
  )

  def mainWindow = div(
    child <-- activeWindow.toObservable.map {
      case View.MapPage() => mapWindow
      case View.SearchPage() =>
        BikemapRef.bikeMap.hideMap()
        searchWindow
    }
  )

  def appElement(): Element =
    data.BikeStationData.saveBikeInfo()
    setupMapInteractivity()
    div(
      h1("Bikes Available", switchWindow()),
      mainWindow,
      child <-- ViewState.MapIsVisible.toObservable.map {
        case true =>
          button(
            "Clear Map",
            onClick --> { _ =>
              BikemapRef.bikeMap.clearMap()
            }
          )
        case false => emptyNode
      }
    )
  end appElement

end Main
