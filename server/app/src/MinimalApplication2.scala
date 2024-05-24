package app

import bikeinfo.*

case class MinimalRoutes()(implicit cc: castor.Context,
                           log: cask.Logger) 
extends cask.Routes {

  @cask.get("/")
  def index() = {
    "Hello World!"
  }

  @cask.staticFiles("/assets")
  def staticFileRoutes() = "app/resources/bikeinfo/assets"


  @cask.staticResources(
    "/bikeinfo",
    headers = Seq("Cache-Control" -> "max-age=31536000"
  ))
  def staticResourcesIndex() = "bikeinfo/index.html"

  @cask.get("/bikes")
  def bikes() = {
    val host = "https://gbfs.urbansharing.com/oslobysykkel.no"

    val stasjonStatus = requests.get(s"${host}/station_status.json")
    val stasjonInfo = requests.get(s"${host}/station_information.json")

    val info = JsonParser(stasjonStatus.text(), stasjonInfo.text())
    upickle.default.write(info, indent = 2) 
  }

  initialize()
}

object MinimalRoutesMain extends cask.Main{
  val allRoutes = Seq(MinimalRoutes())
}

