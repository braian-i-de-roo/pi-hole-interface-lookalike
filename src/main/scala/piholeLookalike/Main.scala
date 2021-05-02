package com.braian
package piholeLookalike

import piholeLookalike.component.{Counter, DomainBlocklistCounter, PercentBlockedCounter, QueriesAnsweredByDoughnut, QueriesBlockedCounter, QueryTypesDoughnut, TopBlockedClientsTable, TopBlockedDomainsTable, TopClientsTable, TopPermittedDomainsTable, TotalQueriesBarChart, TotalQueriesCounter}

import com.braian.piholeLookalike.bulma.Card
import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js

object Main extends App{
  val app = dom.document.querySelector("#app")

  val generator = QueryGenerator()
  val queryStream = generator.queries

  val totalQueriesCounter = TotalQueriesCounter(queryStream)
  val blockedQueriesCounter = QueriesBlockedCounter(queryStream)
  val percentBlockedCounter = PercentBlockedCounter(queryStream)
  val domainBlocklistCounter = DomainBlocklistCounter(queryStream)

  val queryTypesCanvas = QueryTypesDoughnut(queryStream)

  val queryAnsweredCanvas = QueriesAnsweredByDoughnut(queryStream)

  val mainNode = section(
    cls := "section",
    div(
      cls := "columns",
      div(
        cls := "column",
        totalQueriesCounter.node
      ),
      div(
        cls := "column",
        blockedQueriesCounter.node
      ),
      div(
        cls := "column",
        percentBlockedCounter.node
      ),
      div(
        cls := "column",
        domainBlocklistCounter.node
      ),
    ),
    Card(Some("Total queries over last 24 hours"), Some(p("hmmm"))).node,
    div(cls := "block"),
    Card(Some("Client activity over last 24 hours"), None).node,
    div(cls := "block"),
    div(cls := "columns",
      div(cls := "column",
        Card(Some("Query Types"), Some(queryTypesCanvas.node)).node,
      ),
      div(cls := "column",
        Card(Some("Queries answered by"), Some(queryAnsweredCanvas.node)).node
      )
    ),
    div(cls := "columns",
      div(cls := "column",
        Card(Some("Top Permitted Domains"), Some(TopPermittedDomainsTable(queryStream).node)).node
      ),
      div(cls := "column",
        Card(Some("Top Blocked Domains"), Some(TopBlockedDomainsTable(queryStream).node)).node
      )
    ),
    div(cls := "columns",
      div(cls := "column",
        Card(Some("Top Clients (Total)"), Some(TopClientsTable(queryStream).node)).node
      ),
      div(cls := "column",
        Card(Some("Top Clients (blocked only)"), Some(TopBlockedClientsTable(queryStream).node)).node
      )
    ),
  )

  render(app, mainNode)
}
