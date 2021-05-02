package com.braian
package piholeLookalike.bulma

import piholeLookalike.bulma
import piholeLookalike.bulma.Color

import com.raquo.laminar.api.L._

class Notification private(val node: Div)

object Notification {
  def apply(content: Element,
            color: bulma.Color = Color.Normal): Notification = {

    val node = div(
      cls := "notification",
      cls := color.value,
      content
    )

    new Notification(node)
  }
}
