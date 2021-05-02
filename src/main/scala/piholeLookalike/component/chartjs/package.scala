package com.braian
package piholeLookalike.component

package object chartjs {
  val colors = Vector("#4285F4", "#DB4437", "#F4B400", "#0F9D58")

  private var colorIndex = 0

  def nextColor: String = {
    val color = colors(colorIndex)
    colorIndex =
      if (colorIndex == colors.length - 1) 0
      else colorIndex + 1
    color
  }

  def nextColors(n: Int): Vector[String] = {
    n match {
      case x if x > 1 =>
        val minusOne = Vector.fill(n - 1)(nextColor)
        val last = nextColor
        if (minusOne.head == last) minusOne :+ nextColor
        else minusOne :+ last
      case x =>
        Vector.fill(x)(nextColor)
    }
  }
}
