package bikeinfo.view

import org.scalajs.dom.KeyboardEvent
import com.raquo.laminar.api.L.{*, given}
import bikeinfo.model.*
import bikeinfo.data.*
import bikeinfo.state.ViewState
import com.raquo.laminar.nodes.ChildNode

object SearchPanel:

  private def pickFirstStation() =
    ViewState.searchTextSignal.map(_.headOption)

  def baseButton(
      buttonMods: Mod[Button]*
  ) =
    button(
      marginLeft := "10px",
      buttonMods
    )

  val searchTextField =
    input(onInput.mapToValue --> ViewState.searchTextBus.writer)

  private def buttonEnabled: Signal[Boolean] =
    ViewState.searchTextBus.events
      .map(_.length() < 2)
      .toSignal(searchTextField.ref.value.length() < 2)

  def showMapButton() = baseButton(
    "Show Map",
    onClick.flatMap { _ =>
      pickFirstStation()
    } --> ViewState.selectedBikeStationBus.writer,
    disabled <-- buttonEnabled
  )

  def closeMapButton() =
    baseButton(
      "Close Map",
      onClick.mapTo(None) -->
        ViewState.selectedBikeStationBus.writer
    )

  def mapButton() = ViewState.MapIsVisible.toObservable.map {
    case true => closeMapButton()
    case false => showMapButton()
  }

  def showTextHint() = 
    ViewState.selectedBikeStationRx.map{
      case None => div("example, type: 'jen', hit <ENTER>")
      case _    => emptyNode
    }

  def searchField() = div(
    "Name or Street: ",
    form(
      searchTextField,
      onSubmit.preventDefault.flatMap { _ =>
        pickFirstStation()
      } --> ViewState.selectedBikeStationBus.writer,
      child <-- mapButton()
    ),
    child <-- showTextHint()
  )

