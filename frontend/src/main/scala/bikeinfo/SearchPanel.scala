package bikeinfo

import be.doeraene.webcomponents.ui5.{*, given}
import be.doeraene.webcomponents.ui5.configkeys.{*, given}
import org.scalajs.dom.KeyboardEvent
import com.raquo.laminar.api.L.{*, given}
import bikeinfo.SearchPanel.selectedBikeStationBus

object SearchPanel:
  
  val filterValueBus: EventBus[String] = new EventBus
  val selectedBikeStationBus: EventBus[Option[BikeStation]] = new EventBus
  val selectedBikeStationRx: Signal[Option[BikeStation]] = 
    selectedBikeStationBus.events.toSignal(None)

  val suggestions: EventStream[Vector[BikeStation]] = 
    filterValueBus.events.map { text =>
      if text.trim.length < 2 then Vector.empty
      else 
        DataExample.bikes
          .filter(_.name.toLowerCase.contains(text.toLowerCase))
  }

  val searchSignal: Signal[Vector[BikeStation]] =
    suggestions.toSignal(Vector.empty)

  def searchField() =
    div(
    "search: ",
    input(
      onInput.mapToValue --> filterValueBus.writer,
      onKeyDown.filter(_.key == "Enter")
        .flatMap { _ =>
          searchSignal.map { v =>
            val bikeStation = v.head
            Some(bikeStation)
          }
        } --> selectedBikeStationBus.writer,
      )
     )

object BTable:

  private val headerLabels =
    List("name", "address", "free docks", "bikes").map(s => th(s))

  private val tableHeader =
    thead(tr(headerLabels:_*))

  private val tableBody =
    tbody(
      children <-- SearchPanel.searchSignal.split(_.stationId) {
        (id, initial, itemSignal) =>
          renderSearchResult(id, itemSignal)
      }
    )

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
      } --> selectedBikeStationBus.writer
    )

  def render() =
    table(
      tableHeader,
      tableBody
    )
  
