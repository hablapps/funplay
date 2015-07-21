package com.hablapps.funplay{

  import scala.language.higherKinds
  import scala.concurrent.{Future, ExecutionContext}
  import play.api.mvc._
  import scalaz.~>

  object `package` {

    type Service[I, F[_], O] = I => F[O]

    implicit class PureAction(action: Action.type){
      
      def pure[F[_], I, O, B](
        service: I => F[O] // Service[I,F,O] causes type inference problems
      )(parser: BodyParser[B],
        fromHttp: Request[B] => I, 
        toHttpFromSuccess: O => Result,
        toHttpFromFailure: PartialFunction[Throwable, Result]
      )(implicit 
        tn: F ~> Future,
        ec: ExecutionContext
      ) = 
        action.async(parser)(
          fromHttp andThen
          service andThen
          tn.apply andThen
          (_.map(toHttpFromSuccess)(ec)) andThen
          (_.recover(toHttpFromFailure)(ec))
        )
    }

  }

}