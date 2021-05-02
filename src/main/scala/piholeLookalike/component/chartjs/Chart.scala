package com.braian
package piholeLookalike.component.chartjs

import com.braian.piholeLookalike.component.chartjs.Chart.{Dataset, Options}
import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.html.Image

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.|

@js.native
@JSGlobal
//@JSImport("chart.js", JSImport.Namespace)
abstract class Chart(ctx: CanvasRenderingContext2D, config: ChartConfig)
  extends js.Object {
  def data: ChartData = js.native
  def destroy(): Unit = js.native
  def update(): Unit = js.native
  def update(config: UpdateConfig): Unit = js.native
  def reset(): Unit = js.native
  def render(): Unit = js.native
  def render(config: UpdateConfig): Unit = js.native
  def stop(): Chart = js.native
  def resize(): Chart = js.native
  def clear(): Chart = js.native
  def toBase64Image(): String = js.native
  def generateLegend(): String = js.native

}

@js.native
@JSGlobal
//@JSImport("chart.js", JSImport.Namespace)
object Chart extends js.Object {
  type Dataset = LineDataset | BarDataset | PieDataset
  type Options = LineOptions | BarOptions

  var defaults: ChartDefaults = js.native
}

trait ChartDefaults extends js.Object {
  var global: js.UndefOr[ChartGlobal] = js.undefined
}

trait ChartGlobal extends js.Object {
  var animation: js.UndefOr[ChartAnimation] = js.undefined
}

trait ChartAnimation extends js.Object {
  var duration: js.UndefOr[Double] = js.undefined
  var easing: js.UndefOr[String] = js.undefined
  var onProgress: js.UndefOr[js.Function] = js.undefined
  var onComplete: js.UndefOr[js.Function] = js.undefined
}

trait UpdateConfig extends js.Object {
  val duration: js.UndefOr[Double] = js.undefined
  val `lazy`: js.UndefOr[Boolean] = js.undefined
  val easing: js.UndefOr[String] = js.undefined
}

trait ChartConfig extends js.Object {
  var `type`: js.UndefOr[String] = js.undefined
  var data: js.UndefOr[ChartData] = js.undefined
  var options: js.UndefOr[Options] = js.undefined
}

trait ChartData extends js.Object {
  var labels: js.Array[String]
  var datasets: js.Array[Dataset]
}

trait BaseOptions extends js.Object {
  var datasets: js.Dictionary[Dataset]
}

// Line

class LineChart(ctx: CanvasRenderingContext2D, config: ChartConfig)
  extends Chart(ctx, config) {

  private def updateDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[LineDataset]
    ds.data.asInstanceOf[js.Array[Double]].push(value)
    ds.backgroundColor
      .asInstanceOf[js.Array[String]]
      .push(com.braian.piholeLookalike.component.chartjs.nextColor)
  }

  private def removeFromDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[LineDataset]
    val arr = ds.data.asInstanceOf[js.Array[Double]]
    val pos = arr.indexOf(value)
    val newArr = arr.remove(pos)
  }

  def addValue(label: String, value: Double): Unit = {
    data.labels.push(label)
    data.datasets.foreach { x =>
      updateDataset(x, value)
    }
    update()
  }

  def removeValue(label: String, value: Double): Unit = {
    val newArray = data.labels.filterNot(_ == label)
    data.labels = newArray
    data.datasets.foreach { x =>
      removeFromDataset(x, value)
    }
  }
}

trait Point extends js.Object {
  var x: js.UndefOr[Double] = js.undefined
  var y: js.UndefOr[Double] = js.undefined
}

trait LineOptions extends LineDataset {
  var scales: js.UndefOr[LineScales] = js.undefined
  var maintainAspectRatio: js.UndefOr[Boolean] = js.undefined
}

trait LineScales extends js.Object {
  var yAxes: js.UndefOr[js.Array[LineYAxes]] = js.undefined
}

trait LineYAxes extends js.Object {
  var stacked: js.UndefOr[Boolean] = js.undefined
}

trait LineDataset extends js.Object {

  var backgroundColor: js.UndefOr[String] = js.undefined
  var borderCapStyle: js.UndefOr[String] = js.undefined
  var borderColor: js.UndefOr[String] = js.undefined
  var borderDash: js.UndefOr[js.Array[Double]] = js.undefined
  var borderDashOffset: js.UndefOr[Double] = js.undefined
  var borderJoinStyle: js.UndefOr[String] = js.undefined
  var borderWidth: js.UndefOr[Double] = js.undefined
  var cubicInterpolationMode: js.UndefOr[String] = js.undefined
  var clip: js.UndefOr[Double | js.Object] = js.undefined
  var data: js.UndefOr[js.Array[Double] | js.Array[Point]] = js.undefined
  var fill: js.UndefOr[Boolean | String] = js.undefined
  var hoverBackgroundColor: js.UndefOr[String] = js.undefined
  var hoverBorderCapStyle: js.UndefOr[String] = js.undefined
  var hoverBorderColor: js.UndefOr[String] = js.undefined
  var hoverBorderDash: js.UndefOr[js.Array[Double]] = js.undefined
  var hoverBorderDashOffset: js.UndefOr[Double] = js.undefined
  var hoverBorderJoinStyle: js.UndefOr[String] = js.undefined
  var hoverBorderWidth: js.UndefOr[Double] = js.undefined
  var label: js.UndefOr[String] = js.undefined
  var lineTension: js.UndefOr[Double] = js.undefined
  var order: js.UndefOr[Double] = js.undefined
  var pointBackgroundColor: js.UndefOr[String] = js.undefined
  var pointBorderColor: js.UndefOr[String] = js.undefined
  var pointBorderWidth: js.UndefOr[Double] = js.undefined
  var pointHitRadius: js.UndefOr[Double] = js.undefined
  var pointHoverBackgroundColor: js.UndefOr[String] = js.undefined
  var pointHoverBorderColor: js.UndefOr[String] = js.undefined
  var pointHoverBorderWidth: js.UndefOr[Double] = js.undefined
  var pointHoverRadius: js.UndefOr[Double] = js.undefined
  var pointRadius: js.UndefOr[Double] = js.undefined
  var pointRotation: js.UndefOr[Double] = js.undefined
  var pointStyle: js.UndefOr[String | Image] = js.undefined
  var showLine: js.UndefOr[Boolean] = js.undefined
  var spanGaps: js.UndefOr[Boolean] = js.undefined
  var steppedLine: js.UndefOr[Boolean | String] = js.undefined
  var xAxisID: js.UndefOr[String] = js.undefined
  var yAxisID: js.UndefOr[String] = js.undefined
}

// Bar

class BarChart(ctx: CanvasRenderingContext2D, config: ChartConfig)
  extends Chart(ctx, config) {
  private def updateDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[BarDataset]
    ds.data.asInstanceOf[js.Array[Double]].push(value)
    ds.backgroundColor
      .asInstanceOf[js.Array[String]]
      .push(com.braian.piholeLookalike.component.chartjs.nextColor)
  }

  private def removeFromDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[BarDataset]
    val arr = ds.data.asInstanceOf[js.Array[Double]]
    val pos = arr.indexOf(value)
    val newArr = arr.remove(pos)
  }

  def addValue(label: String, value: Double): Unit = {
    data.labels.push(label)
    data.datasets.foreach { x =>
      updateDataset(x, value)
    }
    update()
  }

  def removeValue(label: String, value: Double): Unit = {
    val newArray = data.labels.filterNot(_ == label)
    data.labels = newArray
    data.datasets.foreach { x =>
      removeFromDataset(x, value)
    }
  }
}

trait BarDataset extends js.Object {
  var backgroundColor: js.UndefOr[String] = js.undefined
  var borderColor: js.UndefOr[String] = js.undefined
  var borderSkipped: js.UndefOr[String] = js.undefined
  var borderWidth: js.UndefOr[Double | js.Object] = js.undefined
  var data: js.UndefOr[js.Array[Double | js.Array[Double]]] = js.undefined
  var hoverBackgroundColor: js.UndefOr[String] = js.undefined
  var hoverBorderColor: js.UndefOr[String] = js.undefined
  var hoverBorderWidth: js.UndefOr[Double] = js.undefined
  var label: js.UndefOr[String] = js.undefined
  var order: js.UndefOr[Double] = js.undefined
  var xAxisID: js.UndefOr[String] = js.undefined
  var yAxisID: js.UndefOr[String] = js.undefined
  var barPercentage: js.UndefOr[Double] = js.undefined
  var categoryPercentage: js.UndefOr[Double] = js.undefined
  var barThickness: js.UndefOr[Double | String] = js.undefined
  var maxBarThickness: js.UndefOr[Double] = js.undefined
  var minBarLength: js.UndefOr[Double] = js.undefined
  var stack: js.UndefOr[String] = js.undefined
}

trait BarOptions extends BarDataset {
  var scales: js.UndefOr[BarScales] = js.undefined
}

trait BarScales extends js.Object {
  var x: js.UndefOr[BarAxis] = js.undefined
  var y: js.UndefOr[BarAxis] = js.undefined
}

trait BarAxis extends js.Object {
  var offsetGridLines: js.UndefOr[Boolean] = js.undefined
  var stacked: js.UndefOr[Boolean] = js.undefined
  var `type`: js.UndefOr[String] = js.undefined
}

// Pie

class PieChart(ctx: CanvasRenderingContext2D, config: ChartConfig)
  extends Chart(ctx, config) {
  private def updateDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[LineDataset]
    ds.data.asInstanceOf[js.Array[Double]].push(value)
    ds.backgroundColor
      .asInstanceOf[js.Array[String]]
      .push(com.braian.piholeLookalike.component.chartjs.nextColor)
  }

  private def removeFromDataset(dataset: Dataset, value: Double): Unit = {
    val ds = dataset.asInstanceOf[PieDataset]
    val arr = ds.data.asInstanceOf[js.Array[Double]]
    val pos = arr.indexOf(value)
    val newArr = arr.remove(pos)
  }

  def setValues(values: Map[String, Double]): Unit = {

    val n = values.toList.sortBy(_._2)
    val colors = nextColors(n.size)
    val names: List[String] = n.map(_._1)
    val probabilities: List[Double] = n.map(_._2)

    data.labels = js.Array(names: _*)
    data.datasets.foreach { x =>
      val ds = x.asInstanceOf[PieDataset]
      ds.data = js.Array(probabilities: _*)
      ds.backgroundColor = js.Array(colors: _*)
    }

    update()
  }

  def addValue(label: String, value: Double): Unit = {
    data.labels.push(label)
    data.datasets.foreach { x =>
      updateDataset(x, value)
    }
    update()
  }

  def removeValue(label: String, value: Double): Unit = {
    // TODO redo, should delete the correct element and not just the first that matches
    val newArray = data.labels.filterNot(_ == label)
    data.labels = newArray
    data.datasets.foreach { x =>
      removeFromDataset(x, value)
    }
    update()
  }
}

trait PieDataset extends js.Object {
  var backgroundColor: js.UndefOr[String | js.Array[String]] = js.undefined
  var borderAlign: js.UndefOr[String] = js.undefined
  var borderColor: js.UndefOr[String] = js.undefined
  var borderWidth: js.UndefOr[Double] = js.undefined
  var data: js.UndefOr[js.Array[Double]] = js.undefined
  var hoverBackgroundColor: js.UndefOr[String] = js.undefined
  var hoverBorderColor: js.UndefOr[String] = js.undefined
  var hoverBorderWidth: js.UndefOr[Double] = js.undefined
  var weight: js.UndefOr[Double] = js.undefined
  var cutoutPercentage: js.UndefOr[Double] = js.undefined
  var rotation: js.UndefOr[Double] = js.undefined
  var circumference: js.UndefOr[Double] = js.undefined
  var animation: js.UndefOr[PieAnimation] = js.undefined

}

trait PieAnimation extends js.Object {
  var animateRotate: js.UndefOr[Boolean] = js.undefined
  var animateScale: js.UndefOr[Boolean] = js.undefined
}
