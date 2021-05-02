package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.bulma.Color

import com.raquo.laminar.api.L._

class PercentBlockedCounter private(val node: Div)

object PercentBlockedCounter {
  def apply(queries: EventStream[Query]): PercentBlockedCounter = {

    val description = p("Percent Blocked")

    val aux = queries.foldLeft((0.0, 0, 0)){(a, b) =>
      val total = a._3 + 1
      val newBlockedTotal = if (b.isBlocked) a._2 + 1 else a._2
      val newPercent = (newBlockedTotal * 100.0) / total
      (newPercent,newBlockedTotal, total)
    }.map(x => x._1.formatted("%.2f") + "%")

    val counter = Counter(
      aux,
      description,
      Color.Warning
    )

    new PercentBlockedCounter(counter.node)
  }
}
