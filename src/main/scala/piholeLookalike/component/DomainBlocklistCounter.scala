package com.braian
package piholeLookalike.component

import piholeLookalike.bulma.Color

import com.braian.piholeLookalike.Query
import com.raquo.laminar.api.L._
import org.scalajs.dom

class DomainBlocklistCounter private(val node: Div)

object DomainBlocklistCounter {
  def apply(queries: EventStream[Query]): DomainBlocklistCounter = {


    val description = p("Domains on Blocklist")

    val i = Var[Int](0)

    val counterUpdater = () => {
      val r = scala.math.random() * 100000
      i.set(r.intValue)
    }

    dom.window.setInterval(counterUpdater, 50000)

    val counter = Counter(i.signal.map(_.toString), description, Color.Danger)

    new DomainBlocklistCounter(counter.node)
  }
}
