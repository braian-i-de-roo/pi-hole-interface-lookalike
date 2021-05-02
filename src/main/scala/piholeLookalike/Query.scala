package com.braian
package piholeLookalike

case class Query(client: String, target: String, isBlocked: Boolean, tpe: QueryType, answeredBy: String, time: Double)

sealed trait QueryType {
  def value: String
}
case object A extends QueryType {
  override def value: String = "A"
}
case object AAAA extends QueryType {
  override def value: String = "AAAA"
}
case object SRV extends QueryType {
  override def value: String = "SRV"
}
case object PTR extends QueryType {
  override def value: String = "PTR"
}
