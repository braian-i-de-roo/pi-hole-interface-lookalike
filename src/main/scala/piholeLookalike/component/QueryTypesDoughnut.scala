package com.braian
package piholeLookalike.component

import piholeLookalike.Query
import piholeLookalike.component.chartjs.Chart.Dataset
import piholeLookalike.component.chartjs._

import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js

class QueryTypesDoughnut private(val node: Element)

object QueryTypesDoughnut {
  def apply(queries: EventStream[Query]): QueryTypesDoughnut = {

    val queryList = queries.foldLeft(List[Query]())((a, b) => a :+ b)
    val grouped = queryList.map(_.groupMap(_.tpe.value)(z => z))
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
      width := "700",
      height := "700",
    )

    val doughnut = new PieChart(node.ref.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D], chartConfig)

    node.amend(
      onMountCallback(x =>
        grouped.foreach{x =>
          doughnut.setValues(x)
        }(x.owner)
      )
    )

    new QueryTypesDoughnut(node)
  }
}
