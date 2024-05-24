package bikeinfo

import org.scalajs.dom.KeyboardEvent

object Keys:

  val enterPressed = (event: KeyboardEvent) => {
      if (event.key == "Enter") {
        println("Enter key was pressed")
        // Additional actions when Enter is pressed
      }
  }

