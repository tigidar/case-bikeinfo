package bikeinfo.view

import com.raquo.laminar.api.L.{*, given}
import scala.scalajs.js

object SelectInMap:

  val map = BikemapRef.bikeMap

  def render() =
    map.showAll(js.Array())
    div(
      "<Click> in the map to find nearest bikestations"
    )


