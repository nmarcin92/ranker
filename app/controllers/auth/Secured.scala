package controllers.auth

import pdi.jwt._
import play.api.libs.concurrent.Execution.Implicits._
import play.api.mvc.Results._
import play.api.mvc.{ActionBuilder, Request, Result, WrappedRequest}

import scala.concurrent.Future

class AuthenticatedRequest[A](val userInfo: UserAuthInfo, request: Request[A]) extends WrappedRequest[A](request)

trait Secured {
  def Authenticated = AuthenticatedAction
}

object AuthenticatedAction extends ActionBuilder[AuthenticatedRequest] {
  override def invokeBlock[A](request: Request[A],
                              block: (AuthenticatedRequest[A]) => Future[Result]): Future[Result] =
    request.jwtSession.getAs[UserAuthInfo]("user") match {
      case Some(user) => block(new AuthenticatedRequest(user, request)).map(_.refreshJwtSession(request))
      case _ => Future.successful(Unauthorized)
    }

}
