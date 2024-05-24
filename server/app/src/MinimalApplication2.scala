package app

import bikeinfo.*

case class MinimalRoutes()(implicit cc: castor.Context,
                           log: cask.Logger) 

extends cask.Routes {

  val jsonPath = 
    os.pwd / "app" / "test" / "resources"

  val stasjonStatus = os.read(jsonPath / "stasjon_status.json")
  val stasjonInfo = os.read(jsonPath / "stasjoner.json")

  @cask.get("/")
  def index() = {
    "Hello World!"
  }

  @cask.staticFiles("/assets")
  def staticFileRoutes() = "app/resources/bikeinfo/assets"

  @cask.staticFiles("/files")
  def staticFileRoutes2() = "app/resources/bikeinfo/cask"

  @cask.staticResources(
    "/bikeinfo",
    headers = Seq("Cache-Control" -> "max-age=31536000"
  ))
  def staticResourcesIndex() = "bikeinfo/index.html"

  @cask.get("/bikes")
  def bikes() = {
    val info = JsonParser(stasjonStatus, stasjonInfo)
    upickle.default.write(info, indent = 2) 
  }

  @cask.post("/do-thing")
  def doThing(request: cask.Request) = {
    request.text().reverse
  }

  initialize()
}

object MinimalRoutesMain extends cask.Main{
  val allRoutes = Seq(MinimalRoutes())
}

