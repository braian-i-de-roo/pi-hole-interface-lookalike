package com.braian
package piholeLookalike.component

import piholeLookalike.bulma

import com.raquo.laminar.api.L._

class Counter private(val node: Div,
                      val description: Var[Element],
                      val counter: EventBus[String])

object Counter {
  def apply(query: Observable[String],
            description: Element,
            color: bulma.Color): Counter = {
    val descriptionVar = Var(description)

    val i = new EventBus[String]

    val node = div(
      div(
        p(child <-- descriptionVar),
        p(
          cls := "title is-2",
          query --> i.writer,
          child.text <-- i.events
        )
      ),
      //div(cls := "icon", child <-- iconVar)
    )

    val notification = bulma.Notification(node, color)

    new Counter(notification.node, descriptionVar, i)
  }
}
