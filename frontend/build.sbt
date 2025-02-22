import org.scalajs.linker.interface.ModuleSplitStyle

lazy val bikeinfo = project.in(file("."))
  .enablePlugins(ScalaJSPlugin) // Enable the Scala.js plugin in this project
  .settings(
    scalaVersion := "3.3.1",

    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := true,

    /*
    Compile / fastOptJS / artifactPath := 
      (baseDirectory.value / "src" / "main" / "web" / "scalajs-bundle.js"),
    */

    Compile / fastOptJS / artifactPath := baseDirectory.value / "src" / "main" / "web" / "scalajs-output.js",

    /* Configure Scala.js to emit modules in the optimal way to
     * connect to Vite's incremental reload.
     * - emit ECMAScript modules
     * - emit as many small modules as possible for classes in the "livechart" package
     * - emit as few (large) modules as possible for all other classes
     *   (in particular, for the standard library)
     */
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallModulesFor(List("bikeinfo")))
    },

    /* Depend on the scalajs-dom library.
     * It provides static types for the browser DOM APIs.
     */
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.4.0",
    libraryDependencies += "com.raquo" %%% "laminar" % "17.0.0",
    libraryDependencies += "be.doeraene" %%% "web-components-ui5" % "1.3.0",
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "3.3.1",
    libraryDependencies += "org.scalameta" %%% "munit" % "0.7.29" % Test,

    testFrameworks += new TestFramework("munit.Framework")
  )

