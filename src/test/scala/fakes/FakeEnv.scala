package fakes

import app.LiveController
import service.{LiveExpenseService, LivePersonService}

trait FakeEnv
    extends LiveExpenseService
    with LivePersonService
    with FakeConsole
    with LiveController
