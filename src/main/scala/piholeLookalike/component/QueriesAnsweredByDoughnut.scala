package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.component.chartjs.Chart.Dataset
import piholeLookalike.component.chartjs._

import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js

class QueriesAnsweredByDoughnut private(val node: Element)

object QueriesAnsweredByDoughnut {
  def apply(queries: EventStream[Query]): QueriesAnsweredByDoughnut = {
    val queryList = queries.foldLeft(List[Query]())((a, b) => a :+ b)
    val grouped: Signal[Map[String, Double]] = queryList.map(_.groupMap(_.answeredBy)(z => z))
      .map(_.map(x => (x._1, x._2.size.toDouble)))


    val chartConfig = new ChartConfig {
      `type` = "doughnut"
      data = new ChartData {
        override var labels: js.Array[String] = js.Array()
        override var datasets: js.Array[Dataset] = js.Array(new PieDataset {
          data = js.Array[Double]()
        })
      }
      options = new LineOptions {
        maintainAspectRatio = false
      }
    }

    val node = canvas(
      width := "400",
      height := "400",
    )

    val doughnut = new PieChart(node.ref.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D], chartConfig)

    node.amend(
      onMountCallback(x =>
        grouped.foreach { values =>
          doughnut.setValues(values)
        }(x.owner)
      )
    )

    new QueriesAnsweredByDoughnut(node)
  }
}
