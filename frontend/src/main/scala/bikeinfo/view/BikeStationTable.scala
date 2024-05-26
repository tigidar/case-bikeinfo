package bikeinfo.view

import com.raquo.laminar.api.L.{*, given}
import bikeinfo.model.*
import bikeinfo.state.ViewState

object BikeStationTable:

  private val headerLabels =
    List("name", "address", "free docks", "bikes").map(s => th(s))

  private val tableHeaderColumns =
    thead(tr(headerLabels:_*))

  private def renderSearchResult(
    id: Int, st: Signal[BikeStation]
  ): Element =
    tr(
      td(child.text <-- st.map(_.name)),
      td(child.text <-- st.map(_.address)),
      td(child.text <-- st.map(_.availableBikes)),
      td(child.text <-- st.map(_.availableDocks)),
      onClick.flatMap { _ =>
        st.map { bs =>
          Some(bs)
        }
      } --> ViewState.selectedBikeStationBus.writer
    )

  private val tableBody =
    tbody(
      children <-- ViewState.searchTextSignal.split(_.stationId) {
        (id, initial, itemSignal) =>
          renderSearchResult(id, itemSignal)
      }
    )

  def tableHeader() =
    ViewState.searchTextSignal.map(_.size).map { nrOfElements =>
      if nrOfElements < 1 then emptyNode else tableHeaderColumns
    }

  def render() =
    table(
      child <-- tableHeader(),
      tableBody
    )
  
