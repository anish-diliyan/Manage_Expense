package fpfinal.app

import cats._
import cats.implicits._
import fpfinal.app.Configuration.{AppOp, readEnv}
import fpfinal.app.Syntax._

object App {
  val ME: MonadError[AppOp, String] = MonadError[AppOp, String]

  private val mkOptionsString: List[Command] => String = commands =>
    ("Please select an option: \n" :: commands.zipWithIndex.map {
      case (c, i) => s"($i) ${c.show}"
    }).mkString("\n", "\n", "\n")

  private val printOptions: AppOp[Unit] = readEnv.flatMap { env =>
    val allCommands = env.controller.getAllCommands.toList
    val options = mkOptionsString(allCommands)
    env.console.printLine(options).toAppOp
  }

  private val readCommandNumber: AppOp[Int] = readEnv.flatMap { env =>
    env.console.readLine("Your option: ").toAppOp.flatMap { option =>
      ME.fromOption(option.toIntOption, "Invalid option selected")
    }
  }

  private val executeCommand: AppOp[Boolean] = readEnv.flatMap { env =>
    val commandNumberOp = readCommandNumber
    val commandOp = commandNumberOp.flatMap { commandNumber =>
      ME.fromOption(
        env.controller.getCommandByNumber(commandNumber),
        "Command not found"
      )
    }

    for {
      _ <- printOptions
      command <- commandOp
      successMsg <- command.execute()
      _ <- env.console.printLine(s"\n$successMsg\n", Console.Success).toAppOp
    } yield command.isExit
  }


  private val executeCommandWithRecovery: AppOp[Boolean] =
    ME.handleErrorWith(executeCommand) { error =>
      readEnv.flatMap { env =>
        env.console.printLine(s"\n$error\n", Console.Error).as(false).toAppOp
      }
    }

  def run(): AppOp[Unit] =
    ME.iterateUntil(executeCommandWithRecovery)(identity).void

}
