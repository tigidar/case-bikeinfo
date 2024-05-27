package bikeinfo.data

import scala.scalajs.js
import org.scalajs.dom
import bikeinfo.model.*
import scala.concurrent.*
import ExecutionContext.Implicits.global

object BikeStationData:

    //val url = "http://localhost:8080/bikes"  // URL where Vite will serve the JSON file
    val url = "http://localhost:8080/bikes"  // URL where Vite will serve the JSON file
    // Function to retrieve data from localStorage and parse it
    def getStoredData(): Vector[BikeStation] = {
      val storedData = dom.window.localStorage.getItem("bikes")
      if (storedData != null) {
        upickle.default.read(storedData)
      } else {
        println("No data found in localStorage, using default test data.")
        DataExample.bikes
      }
    }

    def saveBikeInfo() =
      val data = dom.fetch(url).toFuture.map { d =>
        println(d.ok)
        d
      }

      data.map(_.json().toFuture).flatMap(identity).map { json =>
        println(json);
        // Assuming the JSON data is an array
        dom.window.localStorage.setItem("bikes", js.JSON.stringify(json))
      }.recover {
        case exception: Throwable =>
          println(s"Failed to fetch or store data: ${exception.getMessage}")
      }
      getStoredData()

