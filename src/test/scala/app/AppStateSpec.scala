package app

import cats.implicits._
import cats.kernel.laws.discipline.EqTests
import main.FpFinalSpec

class AppStateSpec extends FpFinalSpec {
  checkAll("Eq[AppState]", EqTests[AppState].eqv)
}
