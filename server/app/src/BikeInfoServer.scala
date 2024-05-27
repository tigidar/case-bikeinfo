package app

import bikeinfo.*

case class BikeInfoRoutes()(implicit cc: castor.Context,
                           log: cask.Logger) 
extends cask.Routes {

  @cask.get("/")
  def index() = {
    "Hello World!"
  }

  @cask.staticResources("/marker-icon.png")
  def serveMarkerIcon() =
    "marker-icon.png"

  @cask.staticResources("/marker-shadow.png")
  def serveMarkerShadowIcon() =
    "marker-shadow.png "

  @cask.staticResources("/assets/index-e7bcf3dc.js")
  def staticMainIndex() = 
    "bikeinfo/assets/index-e7bcf3dc.js"

  @cask.staticResources("/assets/index-69420918.css")
  def staticMainCss() = 
    "bikeinfo/assets/index-69420918.css"

  @cask.staticResources(
    "/bikeinfo",
    headers = Seq("Cache-Control" -> "max-age=31536000"
  ))
  def staticResourcesIndex() = "bikeinfo/index.html"

  val clientIdentifier = Map("Client-Identifier" -> "ORIGO-TEST-CASE")

  @cask.get("/bikes")
  def bikes() = {
    val host = "https://gbfs.urbansharing.com/oslobysykkel.no"
    val stasjonStatus = requests.get(
      url = s"${host}/station_status.json",
      headers = clientIdentifier
    )
    val stasjonInfo = requests.get(
      url = s"${host}/station_information.json",
      headers = clientIdentifier
    )
    val info = JsonParser(stasjonStatus.text(), stasjonInfo.text())
    upickle.default.write(info, indent = 2) 
  }

  initialize()
}

object BikeInfoMain extends cask.Main{
  override def host = "0.0.0.0"
  val allRoutes = Seq(BikeInfoRoutes())
}

