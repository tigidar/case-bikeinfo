import mill._, scalalib._

object app extends ScalaModule {
  def scalaVersion = "3.3.1"

  def ivyDeps = Agg(
    ivy"com.lihaoyi::cask:0.9.2",
    ivy"com.lihaoyi::upickle:3.3.1",
    ivy"com.lihaoyi::ujson:3.3.1",
    ivy"com.lihaoyi::os-lib:0.9.1",
    ivy"com.lihaoyi::pprint:0.7.0",
    ivy"com.lihaoyi::sourcecode:0.3.1",
  )

  object test extends ScalaTests with TestModule.Utest{

    def ivyDeps = Agg(
      ivy"com.lihaoyi::utest::0.8.1",
      ivy"com.lihaoyi::requests::0.8.0",
    )
  }
}
