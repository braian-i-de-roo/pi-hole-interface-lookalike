package com.braian
package piholeLookalike

package object bulma {
  sealed trait Color {
    def value: String
  }

  object Color {
    case object Primary extends Color {
      override def value: String = "is-primary"
    }

    case object Normal extends Color {
      override def value: String = "is-normal"
    }

    case object Info extends Color {
      override def value: String = "is-info"
    }

    case object Link extends Color {
      override def value: String = "is-link"
    }

    case object Warning extends Color {
      override def value: String = "is-warning"
    }

    case object Danger extends Color {
      override def value: String = "is-danger"
    }

    case object Success extends Color {
      override def value: String = "is-success"
    }
  }
}
