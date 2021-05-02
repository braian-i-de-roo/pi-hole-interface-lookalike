package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.bulma.Color

import com.raquo.laminar.api.L._

class QueriesBlockedCounter private(val node: Div)

object QueriesBlockedCounter {
  def apply(queries: EventStream[Query]): QueriesBlockedCounter = {

    val description = p("Queries Blocked")

    val counter = Counter(
      queries.foldLeft(0.0)((a, b) => if (b.isBlocked) a +1 else a).map(_.toString),
      description,
      Color.Info)

    new QueriesBlockedCounter(counter.node)
  }
}
