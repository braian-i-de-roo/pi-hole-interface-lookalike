package com.braian
package piholeLookalike

import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js.Date
import scala.util.Random

class QueryGenerator private(val queries: EventStream[Query])

object QueryGenerator {
  def apply(): QueryGenerator = {

    val queries = new EventBus[Query]

    val f = () => {
      val isBlocked = randomBlocked
      queries.writer.onNext(Query(
        client = randomClient,
        target = randomTarget,
        isBlocked = isBlocked,
        tpe = randomQueryType,
        answeredBy = if (isBlocked) "blocklist" else randomAnsweredBy,
        time = Date.now()
      ))
    }

    dom.window.setInterval(f, 5000)

    new QueryGenerator(queries.events)
  }

  val clients = List(
    "braian.pc",
    "braian.pi",
    "braian.netbook",
    "daiana.pc",
    "daiana.cel",
    "sergio.cel",
    "adriana.pc"
  )

  val targets: List[String] = List(
    "www.google.com",
    "www.reddit.com",
    "github.com",
    "beautiful-perfect.great-site.net",
    "app.clickup.com",
    "www.youtube.com",
    "web.whatsapp.com",
    "discord.com"
  )

  def randomClient: String = clients(Random.nextInt(clients.length))
  def randomTarget: String = targets(Random.nextInt(targets.length))
  def randomBlocked: Boolean = if (Random.nextDouble()> 0.7) true else false
  def randomQueryType: QueryType = {
    val r = Random.nextDouble()
    if (r > 0.3) AAAA
    else if (r > 0.2) A
    else if (r > 0.09) PTR
    else SRV
  }
  def randomAnsweredBy: String = {
    val r = Random.nextDouble()
    if (r > 0.3) "cache"
    else if (r > 0.15) "first DNS"
    else "second DNS"
  }
}
