# FunPlay: Purely Functional Programming for Play framework

**FunPlay** is a collection of tools to aid purely functional programming with Play framework. 

Current tools include: 
* Typesafe action builders
* Purely functional controllers
* Scalaz typeclass instances
* ...

## Typesafe action builders

Your service as a function: 

```scala
type Service[I,O] = I => Future[O]
```

Then, we want to build a Play controller for it: 

```scala
val service1: Service[I,O]

val controller1 = Action.async{
  ....
}
```

Modularise the work: translation back and forth from HTTP request/response to Service I/O.
Provide defaults whenever applicable. 

## Purely functional controllers

Now, we defined our effects and our service is no longer a Future-based service, but a Kleisly.

```scala
type Service[F[_], I, O] = I => F[O]
```

When building our controller, we need more information: the interpreter for `F`

## Scalaz typeclass instances

Some instances for common Scalaz typeclasses.

