// Copyright(C) 2018-2019 John A. De Goes. All rights reserved.

package net.degoes.essentials

object types {
  type MyNothing = Nothing

  //
  // EXERCISE 1
  //
  // List all values of the type `Unit`.
  //
  val UnitValues: Set[Unit] = Set(())

  //
  // EXERCISE 2
  //
  // List all values of the type `Nothing`.
  //
  val NothingValues: Set[Nothing] = Set()

  //
  // EXERCISE 3
  //
  // List all values of the type `Boolean`.
  //
  val BoolValues: Set[Boolean] = Set(false, true)

  //
  // EXERCISE 4
  //
  // List all values of the type `Either[Unit, Boolean]`.
  //
  val EitherUnitBoolValues: Set[Either[Unit, Boolean]] = Set(Left(()), Right(false), Right(true))

  //
  // EXERCISE 5
  //
  // List all values of the type `(Boolean, Boolean)`.
  //
  val TupleBoolBoolValues: Set[(Boolean, Boolean)] =
    Set((false, false), (false, true), (true, false), (true, true))

  //
  // EXERCISE 6
  //
  // List all values of the type `Either[Either[Unit, Unit], Unit]`.
  //
  val EitherEitherUnitUnitUnitValues: Set[Either[Either[Unit, Unit], Unit]] = 
    Set(Right(()), Left(Right(())), Left(Left(())))

  //
  // EXERCISE 7
  //
  // Given:
  // A = { true, false }
  // B = { "red", "green", "blue" }
  //
  // List all the elements in `A * B`.
  //
  val AProductB: Set[(Boolean, String)] = Set(
    (true, "red"), (true, "green"), (true, "blue"),
    (false, "red"), (false, "green"), (false, "blue")
  )

  //
  // EXERCISE 8
  //
  // Given:
  // A = { true, false }
  // B = { "red", "green", "blue" }
  //
  // List all the elements in `A + B`.
  //
  val ASumB: Set[Either[Boolean, String]] = Set(
    Left(true), Left(false), Right("red"), Right("green"), Right("blue")
  )

  //
  // EXERCISE 9
  //
  // Create a product type of `Int` and `String`, representing the age and
  // name of a person.
  //
  // type Person1 = ???
  final case class Person2(age: Int, name: String)

  //
  // EXERCISE 10
  //
  // Prove that `A * 1` is equivalent to `A` by implementing the following two
  // functions.
  //
  def to1[A](t: (A, Unit)): A   = t._1
  def from1[A](a: A): (A, Unit) = (a, ())

  //
  // EXERCISE 11
  //
  // Prove that `A * 0` is equivalent to `0` by implementing the following two
  // functions.
  //
  def to2[A](t: (A, Nothing)): Nothing   = ???
  def from2[A](n: Nothing): (A, Nothing) = ???

  //
  // EXERCISE 12
  //
  // Create a sum type of `Int` and `String` representing the identifier of
  // a robot (a number) or the identifier of a person (a name).
  //
  type Identifier1
  sealed trait Identifier2
  final case class Robot(number: Int) extends Identifier2
  final case class Person(name: String) extends Identifier2


  //
  // EXERCISE 13
  //
  // Prove that `A + 0` is equivalent to `A` by implementing the following two
  // functions in a way that loses no information.
  //
  def to3[A](t: Either[A, Nothing]): A   = ???
  def from3[A](a: A): Either[A, Nothing] = ???

  //
  // EXERCISE 14
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // credit card, which has a number, an expiration date, and a security code.
  //
  // type CreditCard = ???
  final case class CreditCard2(number: BigInt, expiration: java.time.LocalDate, securityCode: BigInt)

  //
  // EXERCISE 15
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // payment method, which could be a credit card, bank account, or
  // cryptocurrency.
  //
  // type PaymentMethod = ???
  sealed trait PaymentMethod2
  case object CredidC extends PaymentMethod2
  case object BankA extends PaymentMethod2
  case object Cryptocurrency extends PaymentMethod2

  //
  // EXERCISE 16
  //
  // Create either a sum type or a product type (as appropriate) to represent an
  // employee at a company, which has a title, salary, name, and employment date.
  //
  // type Employee = ???
  final case class Employee2(title: String, salary: Int, name: String, employmentDate: java.time.LocalDate)

  //
  // EXERCISE 17
  //
  // Create either a sum type or a product type (as appropriate) to represent a
  // the rank of a piece on a chess board, which could be a pawn, rook, bishop,
  // knight, queen, or king.
  //
  // type ChessPieceRank = ???
  sealed trait Rank
  case object Pawn extends Rank
  case object Rook extends Rank
  case object Bishop extends Rank
  case object Knight extends Rank
  case object Queen extends Rank
  case object King extends Rank

  //
  // EXERCISE 18
  //
  // Create a "smart constructor" for `Programmer` that only permits levels
  // that are non-negative.
  //
  sealed abstract case class Programmer private (level: Int)
  object Programmer {
    def make(level: Int): Option[Programmer] = {
      if (level >= 0) Some(new Programmer(level) {}) else None
    }
  }

  //
  // EXERCISE 19
  //
  // Using algebraic data types and smart constructors, make it impossible to
  // construct a `BankAccount` with an illegal (undefined) state in the
  // business domain. Note any limitations in your solution.
  //
  case class BankAccount(ownerId: String, balance: BigDecimal, accountType: String, openedDate: Long)
  sealed trait AccountType
  case object Current extends AccountType
  case object Savings extends AccountType

  sealed abstract case class Balance private (balance: BigDecimal)
  object Balance {
    def make(balance: BigDecimal): Option[Balance] = {
      if (balance < 0) None else Some(new Balance(balance) {})
    }
  }

  sealed abstract case class Owner private (ownerId: String)
  object Owner {
    def make(ownerId: String): Option[Owner] = {
      if (ownerId.size == 3) Some(new Owner(s"000$ownerId"){}) else Some(new Owner(ownerId) {})
    }
  }

  final case class BankAccount2(ownerId: Owner, balance: Balance, accountType: AccountType, openedDate: java.time.LocalDate)

  //
  // EXERCISE 20
  //
  // Create an ADT model of a game world, including a map, a player, non-player
  // characters, different classes of items, and character stats.
  //
  // type GameWorld = ???
}

object functions {
  type ??? = Nothing

  //
  // EXERCISE 1
  //
  // Convert the following non-function into a function.
  //
  def parseInt1(s: String): Int = s.toInt
  def parseInt2(s: String): Option[Int] = scala.util.Try(s.toInt).toOption

  //
  // EXERCISE 2
  //
  // Convert the following non-function into a function.
  //
  def arrayUpdate1[A](arr: Array[A], i: Int, f: A => A): Unit =
    arr.update(i, f(arr(i)))
  def arrayUpdate2[A](arr: Array[A], i: Int, f: A => A): Option[List[A]] =
    if (i <= arr.length) Some(arr.updated(i, f(arr(i))).toList) else None

  //
  // EXERCISE 3
  //
  // Convert the following non-function into a function.
  //
  def divide1(a: Int, b: Int): Int = a / b
  def divide2(a: Int, b: Int): Option[Int] = if (a == 0) None else Some(a/b)

  //
  // EXERCISE 4
  //
  // Convert the following non-function into a function.
  //
  var id = 0
  def freshId1(): Int = {
    val newId = id
    id += 1
    newId
  }
  def freshId2(id: Int): (Int, Int) = (id, id+1)

  //
  // EXERCISE 5
  //
  // Convert the following non-function into a function.
  //
  import java.time.LocalDateTime
  def afterOneHour1: LocalDateTime              = LocalDateTime.now.plusHours(1)
  def afterOneHour2(now: LocalDateTime, hours: Int): LocalDateTime = now.plusHours(hours)

  //
  // EXERCISE 6
  //
  // Convert the following non-function into function.
  //
  def head1[A](as: List[A]): A = {
    if (as.length == 0) println("Oh no, it's impossible!!!")
    as.head
  }
  def head2[A](as: List[A]): Option[A] = as.headOption

  //
  // EXERCISE 7
  //
  // Convert the following non-function into a function.
  //
  trait Account
  trait Processor {
    def charge(account: Account, amount: Double): Unit
  }
  final case class Coffee() {
    final val price = 3.14
  }
  def buyCoffee1(processor: Processor, account: Account): Coffee = {
    val coffee = Coffee()
    processor.charge(account, coffee.price)
    coffee
  }
  final case class Charge[A](account: Account, amount: Double)
  def buyCoffee2(account: Account): Charge[Coffee] = {
    val coffee = Coffee()
    Charge(account, coffee.price)
  }

  //
  // EXERCISE 8
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def printLine(line: String): Unit = ()

  //
  // EXERCISE 9
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def readLine: String = "any constant string"

  //
  // EXERCISE 10
  //
  // Implement the following function under the Scalazzi subset of Scala.
  //
  def systemExit(code: Int): Unit = ()

  //
  // EXERCISE 11
  //
  // Rewrite the following non-function `printer1` into a pure function, which
  // could be used by pure or impure code.
  //
  def printer1(): Unit = {
    println("Welcome to the help page!")
    println("To list commands, type `commands`.")
    println("For help on a command, type `help <command>`")
    println("To exit the help page, type `exit`.")
  }
  def printer2[A](println: String => A, combine: (A, A) => A): A = {
    combine(
      println("Welcome to the help page!"),
      combine(
        println("To list commands, type `commands`."),
        combine(
          println("For help on a command, type `help <command>`"),
          println("To exit the help page, type `exit`.")
          )
        )
      )
  }

  //
  // EXERCISE 12
  //
  // Create a purely-functional drawing library that is equivalent in
  // expressive power to the following procedural library.
  //
  trait Draw {
    def goLeft(): Unit
    def goRight(): Unit
    def goUp(): Unit
    def goDown(): Unit
    def draw(): Unit
    def finish(): List[List[Boolean]]
  }
  def draw1(size: Int): Draw = new Draw {
    val canvas = Array.fill(size, size)(false)
    var x      = 0
    var y      = 0

    def goLeft(): Unit  = x -= 1
    def goRight(): Unit = x += 1
    def goUp(): Unit    = y += 1
    def goDown(): Unit  = y -= 1
    def draw(): Unit = {
      def wrap(x: Int): Int =
        if (x < 0) (size - 1) + ((x + 1) % size) else x % size

      val x2 = wrap(x)
      val y2 = wrap(y)

      canvas.updated(x2, canvas(x2).updated(y2, true))
    }
    def finish(): List[List[Boolean]] =
      canvas.map(_.toList).toList
  }

sealed trait Command
  case object GoLeft extends Command
  case object GoRight extends Command
  case object GoUp extends Command
  case object GoDown extends Command

  def draw2(size: Int, commands: List[Command], drawer: (Array[Array[Boolean]], Command) => Array[Array[Boolean]]): Array[Array[Boolean]] = {
    val canvas = Array.fill(size, size)(false)
    commands.foldLeft(canvas)((canvas, command) => {
      drawer(canvas, command)
    })
  }

}

object parametric {
  def left[A](a: A): Either[A, Nothing]  = Left(a)
  def right[B](b: B): Either[Nothing, B] = Right(b)

  //
  // EXERCISE 1
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def fanout[C, A, B](fst: C => A, snd: C => B): C => (A, B) = 
    (c: C) => (fst(c), snd(c))

  //
  // EXERCISE 2
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def fanin[C, A, B](h: C => (A, B)): (C => A, C => B) = 
    (c => h(c)._1, c => h(c)._2)

  //
  // EXERCISE 3
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def bimap[A, A1, B, B1](f: A => A1, g: B => B1): ((A, B)) => (A1, B1) =
    (t: (A,B)) => (f(t._1), g(t._2))

  //
  // EXERCISE 4
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def either[C, A, B](f: A => C, g: B => C): Either[A, B] => C = 
    (e: Either[A, B]) => e.fold(f,g)

  //
  // EXERCISE 5
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def uneither[C, A, B](h: Either[A, B] => C): (A => C, B => C) =
    (a => h(Left(a)), b => h(Right(b)))

  //
  // EXERCISE 6
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def distRight[C, A, B]: Either[(A, C), (B, C)] => (Either[A, B], C) = {
    case Left((a, c)) => (Left(a), c)
    case Right((b, c)) => (Right(b), c)
  }

  //
  // EXERCISE 7
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def distLeft[C, A, B]: ((Either[A, B], C)) => Either[(A, C), (B, C)] =
    ???

  // EXERCISE 7.2
  def choice[A, A1, B, B1](f: A => A1, g: B => B1): Either[A, B] => Either[A1, B1] =
    ???

  //
  // EXERCISE 8
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def curry[C, A, B](f: ((C, A)) => B): C => A => B =
    ???

  //
  // EXERCISE 9
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def uncurry[C, A, B](f: C => A => B): ((C, A)) => B =
    ???

  //
  // EXERCISE 10
  //
  // Implement the following higher-order, parametrically polymorphic function.
  //
  def compose[A, B, C](f: B => C, g: A => B): A => C =
    ???

  //
  // EXERCISE 11
  //
  // Implement the following higher-order, parametrically polymorphic function.
  // After you implement the function, interpret its meaning.
  //
  def alt[E1, E2, A, B](l: Parser[E1, A], r: E1 => Parser[E2, B]): Parser[(E1, E2), Either[A, B]] =
    Parser { s =>
      l.run(s) match {
        case Left(e1) => r(e1).run(s) match {
          case Left(e2) => Left((e1, e2))
          case Right((s1, b)) => Right((s1, Right(b)))
        }
        case Right((s1, a)) => Right((s1, Left(a)))
      }
    }
  case class Parser[+E, +A](run: String => Either[E, (String, A)])
  object Parser {
    final def fail[E](e: E): Parser[E, Nothing] =
      Parser(_ => Left(e))

    final def succeed[A](a: => A): Parser[Nothing, A] =
      Parser(input => Right((input, a)))

    final def char: Parser[Unit, Char] =
      Parser(
        input =>
          if (input.length == 0) Left(())
          else Right((input.drop(1), input.charAt(0)))
      )
  }

  //
  // EXERCISE 12
  //
  // Create a polymorphic function of two type parameters `A` and `B` called
  // `snd` that returns the second element out of any pair of `A` and `B`.
  //
  object snd {
    def apply[A, B](t: (A, B)): B = ???
  }
  snd((1, "foo"))            // "foo"
  snd((true, List(1, 2, 3))) // List(1, 2, 3)

  //
  // EXERCISE 13
  //
  // Create a polymorphic function called `repeat` that can take any
  // function `A => A`, and apply it repeatedly to a starting value
  // `A` the specified number of times.
  //
  object repeat {
    def apply[A](n: Int)(a: A, f: A => A): A =
      ???
  }
  repeat[Int](100)(0, _ + 1)                           // 100
  repeat[String](10)("", _ + "*")                      // "**********"
  repeat[Int => Int](100)(identity, _ andThen (_ + 1)) // (_ + 100)

  //
  // EXERCISE 14
  //
  // Count the number of unique implementations of the following method.
  //
  def countExample1[A, B](a: A, b: B): Either[A, B] = ???
  val countExample1Answer                           = ???

  //
  // EXERCISE 15
  //
  // Count the number of unique implementations of the following method.
  //
  def countExample2[A, B](f: A => B, g: A => B, a: A): B = ???
  val countExample2Answer = ???

  //
  // EXERCISE 16
  //
  // Implement the function `groupBy1`.
  //
  val TestData =
    "poweroutage;2018-09-20;level=20" :: Nil
  val ByDate: String => String =
    (data: String) => data.split(";")(1)
  val Reducer: (String, List[String]) => String =
    (date, events) =>
      "On date " +
        date + ", there were " +
        events.length + " power outages"
  val ExpectedResults =
    Map(
      "2018-09-20" ->
        "On date 2018-09-20, there were 1 power outages"
    )
  def groupBy1(events: List[String], by: String => String)(
    reducer: (String, List[String]) => String
  ): Map[String, String] = events.groupBy(by).map{ case (date, events) => date -> reducer(date, events)}
  groupBy1(TestData, ByDate)(Reducer) == ExpectedResults

  //
  // EXERCISE 17
  //
  // Make the function `groupBy1` as polymorphic as possible and implement
  // the polymorphic function. Compare to the original.
  //
  // groupBy2(TestData, ByDate)(Reducer) == ExpectedResults
}

object higher_kinded {
  type ??          = Nothing
  type ???[A]      = Nothing
  type ????[A, B]  = Nothing
  type ?????[F[_]] = Nothing

  trait `* => *`[F[_]]
  trait `[*, *] => *`[F[_, _]]
  trait `(* => *) => *`[T[_[_]]]

  //
  // EXERCISE 1
  //
  // Identify a type constructor that takes one type parameter of kind `*`
  // (i.e. has kind `* => *`), and place your answer inside the square brackets.
  //
  type Answer1 = `* => *`[Option]

  //
  // EXERCISE 2
  //
  // Identify a type constructor that takes two type parameters of kind `*` (i.e.
  // has kind `[*, *] => *`), and place your answer inside the square brackets.
  //
  type Answer2 = `[*, *] => *`[Either]

  //
  // EXERCISE 3
  //
  // Create a new type called `Answer3` that has kind `*`.
  //
  trait Answer3 /*[]*/    // no type parameters so already done 

  //
  // EXERCISE 4
  //
  // Create a trait with kind `[*, *, *] => *`.
  //
  trait Answer4[A, B, C] /*[]*/

  //
  // EXERCISE 5
  //
  // Create a new type that has kind `(* => *) => *`.
  //
  trait Bar[A[_]]
  type Answer5 = `(* => *) => *`[Bar]

  //
  // EXERCISE 6
  //
  // Create a trait with kind `[* => *, (* => *) => *] => *`.
  //
  trait Answer6 /*[]*/

  //
  // EXERCISE 7
  //
  // Create an implementation of the trait `CollectionLike` for `List`.
  //
  trait CollectionLike[F[_]] {
    def empty[A]: F[A]

    def cons[A](a: A, as: F[A]): F[A]

    def uncons[A](as: F[A]): Option[(A, F[A])]

    final def singleton[A](a: A): F[A] =
      cons(a, empty[A])

    final def append[A](l: F[A], r: F[A]): F[A] =
      uncons(l) match {
        case Some((l, ls)) => append(ls, cons(l, r))
        case None          => r
      }

    final def filter[A](fa: F[A])(f: A => Boolean): F[A] =
      bind(fa)(a => if (f(a)) singleton(a) else empty[A])

    final def bind[A, B](fa: F[A])(f: A => F[B]): F[B] =
      uncons(fa) match {
        case Some((a, as)) => append(f(a), bind(as)(f))
        case None          => empty[B]
      }

    final def fmap[A, B](fa: F[A])(f: A => B): F[B] = {
      val single: B => F[B] = singleton[B](_)

      bind(fa)(f andThen single)
    }
  }
  val ListCollectionLike: CollectionLike[List] = 
    new CollectionLike[List] {
      def cons[A](a: A, as: List[A]): List[A] = a::as
      def empty[A]: List[A] = Nil
      def uncons[A](as: List[A]): Option[(A, List[A])] = as match {
        case x::xs => Some((x, xs))
        case Nil => None
      }
    }

  //
  // EXERCISE 8
  //
  // Implement `Sized` for `List`.
  //
  trait Sized[F[_]] {
    // This method will return the number of `A`s inside `fa`.
    def size[A](fa: F[A]): Int
  }
  val ListSized: Sized[List] = 
    new Sized[List] {
      def size[A](fa: List[A]): Int = fa.size
    }

  //
  // EXERCISE 9
  //
  // Implement `Sized` for `Map`, partially applied with its first type
  // parameter to `String`.
  //
  type MapString[A] = Map[String, A]
  val MapStringSized: Sized[MapString] = ???

  //
  // EXERCISE 10
  //
  // Implement `Sized` for `Map`, partially applied with its first type
  // parameter to a user-defined type parameter.
  //
  def MapSized2[K]: Sized[Map[K, ?]] =
    ???

  //
  // EXERCISE 11
  //
  // Implement `Sized` for `Tuple3`.
  //
  def Tuple3Sized[C, B]: Sized[(C, B, ?)] = new Sized[Tuple3[C, B, ?]] {
      def size[A](fa: (C, B, A)): Int = 3
    }
}

object example {
  import scala.annotation._ 

  @implicitNotFound("Please define an implementation of Combinable for your data type")
  trait Combinable[A] {
    /**
     * The combine operator must be associative, that is:
     * `combine(a, combine(b, c)) == combine(combine(a, b), c)`
     */
    def combine(left: => A, right: => A): A

    final def associativityLaw(a: A, b: A, c: A): Boolean =
      combine(a, combine(b, c)) == 
        combine(combine(a, b), c)
  }
  object Combinable {
    def apply[A](implicit combinable: Combinable[A]): Combinable[A] = combinable

    implicit val CombinableString: Combinable[String] = 
      new Combinable[String] {
        def combine(left: => String, right: => String): String = 
          left + right
      }

    implicit val CombinableInt: Combinable[Int] = 
      new Combinable[Int] {
        def combine(left: => Int, right: => Int): Int = 
          left + right
      }
  }
  implicit class CombinableSyntax[A](left: => A) {
    def combine(right: => A)(implicit combinable: Combinable[A]): A = 
      combinable.combine(left, right)

    def |+|(right: => A)(implicit combinable: Combinable[A]): A = 
      combine(right)
  }
  final case class MultInt(value: Int) extends AnyVal
  object MultInt {
    implicit val CombinableMultInt: Combinable[MultInt] = 
      new Combinable[MultInt] {
        def combine(left: => MultInt, right: => MultInt): MultInt = 
          MultInt(left.value * right.value)
      }
  }

  final case class Person(name: String, age: Int)
  object Person {
    implicit val CombinablePerson: Combinable[Person] = 
      new Combinable[Person] {
        def combine(left: => Person, right: => Person): Person = 
          Person(left.name + right.name, left.age + right.age)
      }
    }

  def retried[A: Combinable](n: Int)(a: A): A =
    if (n <= 0) a else a combine retried(n - 1)(a)

  trait Task[+E, +A] { self =>
    def run(): Either[E, A]

    def fallback[E1, A1 >: A](that: => Task[E1, A1]): Task[E1, A1] = 
      new Task[E1, A1] {
        def run(): Either[E1, A1] = 
          self.run() match {
            case Left(e1) => that.run() 
            case Right(a) => Right(a)
          }
      }
  }
  object Task {
    implicit def CombinableTask[E, A]: Combinable[Task[E, A]] =
      new Combinable[Task[E, A]] {
        def combine(left: => Task[E, A], right: => Task[E, A]): Task[E, A] = 
          left fallback right
      }
  }
  type InfallibleTask[+A] = Task[Nothing, A]

  def retriedTask[E, A](retryCount: Int)(task: Task[E, A]): Task[E, A] =
    retried[Task[E, A]](retryCount)(task)(_ fallback _)

  def executeTask[E, A](task: Task[E, A]): Either[E, A] = 
    task.run()

}

object tc_motivating {
  /*
  A type class is a tuple of three things:

  1. A set of types and / or type constructors.
  2. A set of operations on values of those types.
  3. A set of laws governing the operations.

  A type class instance is an instance of a type class for a given
  set of types.

   */

  /**
   * All implementations are required to satisfy the transitivityLaw.
   *
   * Transitivity Law:
   * forall a b c.
   *   lt(a, b) && lt(b, c) ==>
   *     lt(a, c)
   */
  trait LessThan[A] {
    def lt(l: A, r: A): Boolean

    final def transitivityLaw(a: A, b: A, c: A): Boolean =
      lt(a, c) || !lt(a, b) || !lt(b, c)
  }
  implicit class LessThanSyntax[A](l: A) {
    def <(r: A)(implicit A: LessThan[A]): Boolean  = A.lt(l, r)
    def >=(r: A)(implicit A: LessThan[A]): Boolean = !A.lt(l, r)
  }
  object LessThan {
    def apply[A](implicit A: LessThan[A]): LessThan[A] = A

    implicit val LessThanInt: LessThan[Int] = new LessThan[Int] {
      def lt(l: Int, r: Int): Boolean = l < r
    }
    implicit def LessThanList[A: LessThan]: LessThan[List[A]] = new LessThan[List[A]] {
      def lt(l: List[A], r: List[A]): Boolean =
        (l, r) match {
          case (Nil, Nil)         => false
          case (Nil, _)           => true
          case (_, Nil)           => false
          case (l :: ls, r :: rs) => l < r && lt(ls, rs)
        }
    }
  }

  def sort[A: LessThan](l: List[A]): List[A] = l match {
    case Nil => Nil
    case x :: xs =>
      val (lessThan, notLessThan) = xs.partition(_ < x)

      sort(lessThan) ++ List(x) ++ sort(notLessThan)
  }

  final case class Person(name: String, age: Int)
  object Person {
    implicit val PersonLessThan: LessThan[Person] = ???
  }

  object oop {
    trait Comparable[A] {
      def lessThan(a: A): Boolean
    }
    def sortOOP[A <: Comparable[A]](l: List[A]): List[A] =
      ???
    case class Person(name: String, age: Int) extends Comparable[Person] {
      def lessThan(a: Person): Boolean = ???
    }
  }

  sort(List(1, 2, 3))
  sort(List(List(1, 2, 3), List(9, 2, 1), List(1, 2, 9)))
}

object hashmap {
  trait Eq[A] {
    def eq(l: A, r: A): Boolean

    def transitivityLaw(a: A, b: A, c: A): Boolean =
      eq(a, c) || !eq(a, b) || !eq(b, c)

    def identityLaw(a: A): Boolean =
      eq(a, a)

    def reflexivityLaw(a: A, b: A): Boolean =
      eq(a, b) == eq(b, a)
  }
  object Eq {
    def apply[A](implicit A: Eq[A]): Eq[A] = A

    implicit val EqInt: Eq[Int] =
      new Eq[Int] {
        def eq(l: Int, r: Int): Boolean = l == r
      }
    implicit val EqString: Eq[String] =
      new Eq[String] {
        def eq(l: String, r: String): Boolean = l == r
      }
  }
  final case class IgnoreCase(value: String)
  object IgnoreCase {
    implicit val EqIgnoreCase: Eq[IgnoreCase] =
      new Eq[IgnoreCase] {
        def eq(l: IgnoreCase, r: IgnoreCase): Boolean =
          l.value.toLowerCase == r.value.toLowerCase
      }
  }

  implicit class EqSyntax[A](l: A) {
    def ===(r: A)(implicit A: Eq[A]): Boolean = A.eq(l, r)
  }

  trait Hash[A] extends Eq[A] {
    def hash(a: A): Int

    final def hashConsistencyLaw(a1: A, a2: A): Boolean =
      (hash(a1) === hash(a2)) || !eq(a1, a2)
  }
  object Hash {
    def apply[A](implicit A: Hash[A]): Hash[A] = A

    implicit val HashInt: Hash[Int] =
      new Hash[Int] {
        def hash(a: Int): Int = a

        def eq(l: Int, r: Int): Boolean = l == r
      }
    implicit val HashString: Hash[String] =
      new Hash[String] {
        def hash(a: String): Int = a.hashCode

        def eq(l: String, r: String): Boolean = l == r
      }
  }
  implicit class HashSyntax[A](val a: A) extends AnyVal {
    def hash(implicit A: Hash[A]): Int = A.hash(a)
  }

  case class Person(age: Int, name: String)
  object Person {
    implicit val HashPerson: Hash[Person] = ???
  }

  class HashPerson(val value: Person) extends AnyVal
  object HashPerson {
    implicit val HashHashPerson: Hash[HashPerson] = ???
  }

  class HashMap[K, V] {
    def size: Int = ???

    def insert(k: K, v: V)(implicit K: Hash[K]): HashMap[K, V] = ???
  }
  object HashMap {
    def empty[K, V]: HashMap[K, V] = ???
  }

  Hash[Int].hash(3)

  trait Hashable {
    def hash: Int
  }

  class HashMapOOP[K <: Hashable, V] {
    def size: Int = ???

    def insert(k: K, v: V): HashMap[K, V] = ???
  }
}

object typeclasses {

  /**
   * {{
   * Reflexivity:   a ==> equals(a, a)
   *
   * Transitivity:  equals(a, b) && equals(b, c) ==>
   *                equals(a, c)
   *
   * Symmetry:      equals(a, b) ==> equals(b, a)
   * }}
   */
  trait Eq[A] {
    def equals(l: A, r: A): Boolean
  }
  object Eq {
    def apply[A](implicit eq: Eq[A]): Eq[A] = eq

    implicit val EqInt: Eq[Int] = new Eq[Int] {
      def equals(l: Int, r: Int): Boolean = l == r
    }
    implicit def EqList[A: Eq]: Eq[List[A]] =
      new Eq[List[A]] {
        def equals(l: List[A], r: List[A]): Boolean =
          (l, r) match {
            case (Nil, Nil) => true
            case (Nil, _)   => false
            case (_, Nil)   => false
            case (l :: ls, r :: rs) =>
              Eq[A].equals(l, r) && equals(ls, rs)
          }
      }
  }
  implicit class EqSyntax[A](val l: A) extends AnyVal {
    def ===(r: A)(implicit eq: Eq[A]): Boolean =
      eq.equals(l, r)
  }

  //
  // Scalaz 7 Encoding
  //
  sealed trait Ordering
  case object EQUAL extends Ordering
  case object LT    extends Ordering
  case object GT    extends Ordering
  object Ordering {
    implicit val OrderingEq: Eq[Ordering] = new Eq[Ordering] {
      def equals(l: Ordering, r: Ordering): Boolean = l == r
    }
  }

  trait Ord[A] {
    def compare(l: A, r: A): Ordering

    final def eq(l: A, r: A): Boolean  = compare(l, r) == EQUAL
    final def lt(l: A, r: A): Boolean  = compare(l, r) == LT
    final def lte(l: A, r: A): Boolean = lt(l, r) || eq(l, r)
    final def gt(l: A, r: A): Boolean  = compare(l, r) == GT
    final def gte(l: A, r: A): Boolean = gt(l, r) || eq(l, r)

    final def transitivityLaw1(a: A, b: A, c: A): Boolean =
      (lt(a, b) && lt(b, c) == lt(a, c)) ||
        (!lt(a, b) || !lt(b, c))

    final def transitivityLaw2(a: A, b: A, c: A): Boolean =
      (gt(a, b) && gt(b, c) == gt(a, c)) ||
        (!gt(a, b) || !gt(b, c))

    final def equalityLaw(a: A, b: A): Boolean =
      (lt(a, b) && gt(a, b) == eq(a, b)) ||
        (!lt(a, b) || !gt(a, b))
  }
  object Ord {
    def apply[A](implicit A: Ord[A]): Ord[A] = A

    implicit val OrdInt: Ord[Int] = new Ord[Int] {
      def compare(l: Int, r: Int): Ordering =
        if (l < r) LT else if (l > r) GT else EQUAL
    }
  }
  implicit class OrdSyntax[A](val l: A) extends AnyVal {
    def =?=(r: A)(implicit A: Ord[A]): Ordering =
      A.compare(l, r)

    def <(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), LT)

    def <=(r: A)(implicit A: Ord[A]): Boolean =
      (l < r) || (this === r)

    def >(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), GT)

    def >=(r: A)(implicit A: Ord[A]): Boolean =
      (l > r) || (this === r)

    def ===(r: A)(implicit A: Ord[A]): Boolean =
      Eq[Ordering].equals(A.compare(l, r), EQUAL)

    def !==(r: A)(implicit A: Ord[A]): Boolean =
      !Eq[Ordering].equals(A.compare(l, r), EQUAL)
  }
  case class Person(age: Int, name: String)
  object Person {
    implicit val OrdPerson: Ord[Person] = new Ord[Person] {
      def compare(l: Person, r: Person): Ordering =
        if (l.age < r.age) LT
        else if (l.age > r.age) GT
        else if (l.name < r.name) LT
        else if (l.name > r.name) GT
        else EQUAL
    }
    implicit val EqPerson: Eq[Person] = new Eq[Person] {
      def equals(l: Person, r: Person): Boolean =
        l == r
    }
  }

  //
  // EXERCISE 1
  //
  // Write a version of `sort1` called `sort2` that uses the polymorphic `List`
  // type, and which uses the `Ord` type class, including the compare syntax
  // operator `<` to compare elements.
  //
  def sort1(l: List[Int]): List[Int] = l match {
    case Nil => Nil
    case x :: xs =>
      val (lessThan, notLessThan) = xs.partition(_ < x)

      sort1(lessThan) ++ List(x) ++ sort1(notLessThan)
  }
  def sort2[A: Ord](l: List[A]): List[A] = l match {
    case Nil => Nil
    case x :: xs =>
      val (lessThan, notLessThan) = xs.partition(_ < x)

      sort2(lessThan) ++ List(x) ++ sort2(notLessThan)
  }

  //
  // EXERCISE 2
  //
  // Create a data structure and an instance of this type class for the data
  // structure.
  //
  trait PathLike[A] {

    /**
     * Returns a node that describes the specified named
     * child of the parent node.
     */
    def child(parent: A, name: String): A

    /**
     * Returns the node that describes the parent of the
     * specified node, or `None` if the node is the root
     * node.
     */
    def parent(node: A): Option[A]

    /**
     * Returns the node that represents the root of the
     * file system.
     */
    def root: A
  }
  object PathLike {
    def apply[A](implicit A: PathLike[A]): PathLike[A] = A
  }
  sealed trait MyPath
  final case object Root extends MyPath
  final case class Child(name: String, parent: MyPath) extends MyPath
  object MyPath {
    implicit val MyPathPathLike: PathLike[MyPath] =
      new PathLike[MyPath] {
        def child(parent: MyPath, name: String): MyPath = Child(name, parent)
        def parent(node: MyPath): Option[MyPath]        = node match {
          case Root => None
          case Child(_, parent) => Some(parent)
        }
        def root: MyPath                                = Root
      }
  }

  //
  // EXERCISE 3
  //
  // Create an instance of the `PathLike` type class for `java.io.File`.
  //
//  implicit val FilePathLike: PathLike[java.io.File] = new PathLike[java.io.File] {
//     import java.io.File
//     def child(parent: MyPath, name: String): MyPath = new File(name, parent)
//     def parent(node: MyPath): Option[MyPath]        = ???
//     def root: MyPath                                = new File("/")
//   }

  //
  // EXERCISE 4
  //
  // Create two laws for the `PathLike` type class.
  //
  trait PathLikeLaws[A] extends PathLike[A] {
    def noCycles(path: A): Boolean = 
      parent(path) match {
        case None => true
        case Some(parent) => noCycles(parent)
      }

    def rootHasNoParent: Boolean = 
      parent(root) match {
        case None => true
        case Some(_) => false
      }

    def parentOfChildOfNodeIsNode(node: A, name: String): Boolean = 
      parent(child(node, name)) match {
        case None => false
        case Some(node0) => node0 == node
      }
  }

  //
  // EXERCISE 5
  //
  // Create a syntax class for path-like values with a `/` method that descends
  // into the given named node.
  //
  implicit class PathLikeSyntax[A](a: A) {
    def /(name: String)(implicit pathLike: PathLike[A]): A =
      pathLike.child(pathLike.root, name)

    def parent(implicit pathLike: PathLike[A]): Option[A] =
      ???
  }
  def root[A: PathLike]: A = PathLike[A].root

  root[MyPath] / "foo" / "bar" / "baz" // MyPath
  (root[MyPath] / "foo").parent        // Option[MyPath]

  //
  // EXERCISE 6
  //
  // Create an instance of the `Filterable` type class for `List`.
  //
  trait Filterable[F[_]] {
    def filter[A](fa: F[A], f: A => Boolean): F[A]
  }
  object Filterable {
    def apply[F[_]](implicit F: Filterable[F]): Filterable[F] = F

    implicit val FilterableList: Filterable[List] = ???
  }

  //
  // EXERCISE 7
  //
  // Create a syntax class for `Filterable` that lets you call `.filterWith` on any
  // type for which there exists a `Filterable` instance.
  //
  implicit class FilterableSyntax[F[_], A](fa: F[A]) {
    ???
  }
  // List(1, 2, 3).filterWith(_ == 2)

  //
  // EXERCISE 8
  //
  // Create an instance of the `Collection` type class for `List`.
  //
  trait Collection[F[_]] {
    def empty[A]: F[A]
    def cons[A](a: A, as: F[A]): F[A]
    def uncons[A](fa: F[A]): Option[(A, F[A])]
  }
  object Collection {
    def apply[F[_]](implicit F: Collection[F]): Collection[F] = F
  }
  implicit val ListCollection: Collection[List] = ???

  val example = Collection[List].cons(1, Collection[List].empty)

  //
  // EXERCISE 9
  //
  // Create laws for the `Collection` type class.
  //
  trait CollectionLaws[F[_]] extends Collection[F] {}

  //
  // EXERCISE 10
  //
  // Create syntax for values of any type that has `Collection` instances.
  // Specifically, add an `uncons` method to such types.
  //
  implicit class CollectionSyntax[F[_], A](fa: F[A]) {
    ???

    def cons(a: A)(implicit F: Collection[F]): F[A] = F.cons(a, fa)
  }
  def empty[F[_]: Collection, A]: F[A] = Collection[F].empty[A]
  // List(1, 2, 3).uncons // Some((1, List(2, 3)))
}
