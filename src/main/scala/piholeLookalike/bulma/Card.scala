package com.braian
package piholeLookalike.bulma

import com.raquo.laminar.api.L._

class Card private(val node: Div)

object Card {
  def apply(initialHeader: Option[String] = None,
            content: Option[Element] = None): Card = {

    val headerVar = Var[Option[String]](initialHeader)
    val headerSignal = headerVar.signal.map {
      case Some(value) => header( cls := "card-header", p( cls := "card-header-title", value))
      case None => emptyNode
    }

    val contentVar = Var[Option[Element]](content)
    val contentSignal = contentVar.signal.map {
      case Some(value) => div(cls := "card-content", value)
      case None => emptyNode
    }
    val node = div(
      cls := "card",
      child <-- headerSignal,
      child <-- contentSignal
    )
    new Card(node)
  }
}
