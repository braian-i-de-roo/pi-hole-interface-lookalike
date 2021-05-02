package com.braian
package piholeLookalike.component

import com.braian.piholeLookalike.Query
import com.braian.piholeLookalike.bulma.Color.{Primary, Success}
import com.braian.piholeLookalike.bulma.ProgressBar
import com.raquo.laminar.api.L._

class TopClientsTable private(val node: Element)

object TopClientsTable {
  def apply(queries: EventStream[Query]): TopClientsTable = {

    val queryList = queries.foldLeft(List[Query]())((a, b) => a :+ b)

    var grouped = queryList.map(_.groupMap(_.client)(z => z))

    val columns = grouped.map{m =>
      val totalCount = m.values.flatten.size
      m.map{ tuple =>
        val count = tuple._2.length
        val percent = (count * 100.0) / totalCount
        (tuple._1, count, ProgressBar(percent, Some(Primary)).node)
      }.toList.sortBy(-_._2).map(x =>
        tr(
          th(x._1),
          th(x._2),
          th(x._3)
        )
      )
    }

    val node = table(
      cls := "table",
      width := "100%",
      thead(
        tr(
          th("Client"),
          th("Requests"),
          th("Frequency")
        )
      ),
      tbody(
        children <-- columns
      )
    )

    new TopClientsTable(node)
  }
}
