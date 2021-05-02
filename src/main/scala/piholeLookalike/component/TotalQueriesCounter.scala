package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.bulma.Color

import com.raquo.laminar.api.L._

class TotalQueriesCounter private(val node: Div)

object TotalQueriesCounter {
  def apply(queries: EventStream[Query]): TotalQueriesCounter = {

    val clients = queries.foldLeft(0)((a, _) => a + 1)

    val description = p(
      "Total queries (",
      child.text <-- clients,
      child.text <-- clients.map(x => if (x ==1) " client)" else " clients)")
    )

    val counter = Counter(queries.foldLeft(0.0)((a, _) => a + 1).map(_.toString), description, Color.Primary)

    new TotalQueriesCounter(counter.node)
  }
}
