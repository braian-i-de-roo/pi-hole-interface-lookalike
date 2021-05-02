package com.braian
package piholeLookalike.component

import com.braian.piholeLookalike.Query
import com.braian.piholeLookalike.component.chartjs.Chart.Dataset
import com.braian.piholeLookalike.component.chartjs.{BarAxis, BarOptions, BarScales, ChartConfig, ChartData, LineChart, LineOptions, PieChart, PieDataset}
import com.raquo.laminar.api.L._
import org.scalajs.dom

import scala.scalajs.js

class TotalQueriesBarChart private(val node: Element)

object TotalQueriesBarChart {
  def apply(queries: EventStream[Query]): TotalQueriesBarChart = {

    val queryList = queries.foldLeft(List[Query]())((a, b) => a :+ b)
    val grouped: Signal[Map[String, Double]] = queryList.map(_.groupMap(_.answeredBy)(z => z))
      .map(_.map(x => (x._1, x._2.size.toDouble)))

    val chartConfig = new ChartConfig {
      `type` = "bar"
      data = new ChartData {
        override var labels: js.Array[String] = js.Array()
        override var datasets: js.Array[Dataset] = js.Array(new PieDataset {
          data = js.Array[Double]()
        })
      }
      options = new BarOptions {
        scales = new BarScales {
          x = new BarAxis {
            `type` = "timeseries"
          }
        }

      }
    }

    val node = canvas(
      width := "400",
      height := "400",
    )

    val lineChart = new LineChart(node.ref.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D], chartConfig)

    new TotalQueriesBarChart(node)
  }
}
