package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.bulma.Color.Success
import piholeLookalike.bulma.ProgressBar

import com.raquo.laminar.api.L._

class TopPermittedDomainsTable private(val node: Element)

object TopPermittedDomainsTable {

  def apply(queries: EventStream[Query]): TopPermittedDomainsTable = {

    val queryList = queries.filter(!_.isBlocked).foldLeft(List[Query]())((a, b) => a :+ b)

    val grouped = queryList.map{_.groupMap(_.target)(z => z)}

    val columns = grouped.map{ m =>
      val totalCount = m.values.flatten.toList.length
      val aux =m.map{ tuple =>
        val count = tuple._2.length
        val percent = (count * 100.0) / totalCount
        (tuple._1, count, ProgressBar(percent, Some(Success)).node)
      }.toList.sortBy(-_._2)
      aux.map(x =>
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
          th("Domain"),
          th("Hits"),
          th("frequency")
        )
      ),
      tbody(
        children <-- columns
      )
    )

    new TopPermittedDomainsTable(node)
  }
}
