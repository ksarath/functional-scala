//sealed trait Piece
//case object X extends Piece
//case object O extends Piece
//
//case class Position private (index: Int)
//object Position {
//  def apply(index: Int): Option[Position] =
//    if (index < 0 || index >= 3) None
//    else Some(new Position(index))
//}
//
//trait Query[+A] { self =>
//  type S
//
//  val initial: S
//  def step(s: S, action: Action): S
//
//  def answer(s: S): A
//
//  final def zip[B](that: Query[B]): Query[(A, B)] =
//    new Query[(A, B)] {
//      type S = (self.S, that.S)
//      val initial: S = (self.initial, that.initial)
//      def step(s: S, action: Action): S =
//        (self.step(s._1, action), that.step(s._2, action))
//      def answer(s: S): (A, B) = (self.answer(s._1), that.answer(s._2))
//    }
//
//  final def *>[B](that: Query[B]): Query[B] = self.zip(that).map(_._2)
//
//  final def <*[B](that: Query[B]): Query[A] = self.zip(that).map(_._1)
//
//  final def map[B](f: A => B): Query[B] =
//    new Query[B] {
//      type S = self.S
//      val initial: S = self.initial
//      def step(s: S, action: Action): S = self.step(s, action)
//      def answer(s: S): B = f(self.answer(s))
//    }
//}
//object Query {
//  final def fold[S0](s0: S0)(f: (S0, Action) => S0): Query[S0] =
//    new Query[S0] {
//      type S = S0
//
//      val initial: S = s0
//      def step(s: S, action: Action): S = f(s, action)
//      def answer(s: S): S = s
//    }
//}
//
//case class Action(x: Position, y: Position, piece: Piece)
//
//case class State(name: String, actions: List[Action]) {
//  final def query[A](q: Query[A]): A =
//    q.answer(actions.foldLeft[q.S](q.initial)(q.step(_, _)))
//}
//
//def wonHorizontal(mark: Piece, y0: Int): Query[Boolean] =
//  (Query.fold[(Boolean, Boolean, Boolean)]((false, false, false)) {
//    case ((b1, b2, b3), Action(x, y, piece)) =>
//
//      (b1 || (piece == mark && x.index == 0 && y.index == y0),
//        b2 || (piece == mark && x.index == 1 && y.index == y0),
//        b3 || (piece == mark && x.index == 2 && y.index == y0))
//  }).map(t => t._1 && t._2 && t._3)
//
//def wonHorizontal(mark: Piece): Query[Boolean] =
//  wonHorizontal(mark, 0).zip(
//    wonHorizontal(mark, 1).zip(
//      wonHorizontal(mark, 2))).map {
//    case (b1, (b2, b3)) => b1 || b2 || b3
//  }
//
//
////package net.degoes
////
////import scalaz.zio._
////import scalaz.zio.console._
////
////object FunctionalScala extends App {
////  sealed trait Piece
////  case object X extends Piece
////  case object O extends Piece
////
////  case class Position private (index: Int)
////  object Position {
////    def apply(index: Int): Option[Position] =
////      if (index < 0 || index >= 3) None
////      else Some(new Position(index))
////  }
////
////  trait Query[+A] { self =>
////    type S
////
////    val initial: S
////    def step(s: S, action: Action): S
////
////    def answer(s: S): A
////
////    final def zip[B](that: Query[B]): Query[(A, B)] =
////      new Query[(A, B)] {
////        type S = (self.S, that.S)
////        val initial: S = (self.initial, that.initial)
////        def step(s: S, action: Action): S =
////          (self.step(s._1, action), that.step(s._2, action))
////        def answer(s: S): (A, B) = (self.answer(s._1), that.answer(s._2))
////      }
////
////    final def *>[B](that: Query[B]): Query[B] = self.zip(that).map(_._2)
////
////    final def <*[B](that: Query[B]): Query[A] = self.zip(that).map(_._1)
////
////    final def map[B](f: A => B): Query[B] =
////      new Query[B] {
////        type S = self.S
////        val initial: S = self.initial
////        def step(s: S, action: Action): S = self.step(s, action)
////        def answer(s: S): B = f(self.answer(s))
////      }
////  }
////  object Query {
////    final def fold[S0](s0: S0)(f: (S0, Action) => S0): Query[S0] =
////      new Query[S0] {
////        type S = S0
////
////        val initial: S = s0
////        def step(s: S, action: Action): S = f(s, action)
////        def answer(s: S): S = s
////      }
////  }
////
////  case class Action(x: Position, y: Position, piece: Piece)
////  case class State(name: String, actions: List[Action]) {
////    final def query[A](q: Query[A]): A =
////      q.answer(actions.foldLeft[q.S])(q.initial)(q.step(_, _)))
////  }
////
////  def wonHorizontal(mark: Piece): Query[Boolean] =
////    (Query.fold[(Boolean, Boolean, Boolean)]((false, false, false))) {
////      case ((b1, b2, b3)), Action(x, y, piece)) =>
////      (
////        b1 || (piece == mark && x.index == 0 && y.index == y0),
////      )
////    }
////
////  def run(args: List[String]): IO[Nothing, ExitStatus] =
////    (for {
////      _ <- putStrLn("Hello World!")
////    } yield ()).redeemPure(_ => ExitStatus.ExitNow(1), _ => ExitStatus.ExitNow(0))
////}
