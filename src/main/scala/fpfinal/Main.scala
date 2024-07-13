package fpfinal

import fpfinal.app.Configuration._
import fpfinal.app.Syntax._
import fpfinal.app.{App, AppState}

object Main {
  // This is the main entry point of the application
  def main(args: Array[String]): Unit = {
    App
      // The following line calls the `run` method on the `App` object/class
      // This method is likely responsible for initializing or starting the application
      .run()
      // The `unsafeRunApp` method is then called on the result of `run`
      // This method takes two arguments:
      .unsafeRunApp(
        // The first argument is `liveEnv`, which is likely an environment or configuration
        // object representing the production/live environment
        liveEnv,
        // The second argument is `AppState.empty`, which represents an initial
        // or empty state for the application
        AppState.empty
      )
  }
}

