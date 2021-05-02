package com.braian
package piholeLookalike.bulma

import com.raquo.laminar.api.L._

class ProgressBar private(val node: Element, val value: Var[Double])

object ProgressBar {
  def apply(initialValue: Double = 0,
            color: Option[Color] = None): ProgressBar = {

    val colorVar = Var[Option[Color]](color)
    val colorSignal: StrictSignal[Option[Color]] = colorVar.signal
    val colorClassSignal = colorSignal.map {
      case Some(value) => value.value
      case None => ""
    }

    val valueVar = Var(initialValue)
    val node = progress(
      cls := "progress",
      cls <-- colorClassSignal,
      value <-- valueVar.signal.map(_.toString),
      maxAttr := "100"
    )
    new ProgressBar(node, valueVar)
  }
}
