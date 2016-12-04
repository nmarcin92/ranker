package controllers.auth

import javax.inject.Inject

import pdi.jwt._
import play.api.mvc.{Action, Controller}
import services.UserService

import scala.concurrent.ExecutionContext.Implicits._

class AuthenticationController @Inject() (val userService: UserService) extends Controller {

  def login = Action.async(parse.json[UserCredentials]) {
    implicit request => {
      val credentials = request.body
      userService.findUserByCredentials(credentials)
        .map(_.map(user => Ok.addingToJwtSession("userInfo", UserAuthInfo(user.username, user.login)))
          .getOrElse(Unauthorized))
    }
  }

  def register = Action.async(parse.json[UserRegistration]) {
    implicit request => {
      val registration = request.body
      userService.registerUser(registration)
        .map(Unit => Ok.addingToJwtSession("userInfo", UserAuthInfo(registration.username, registration.credentials.login)))
        .recover {
          case e: IllegalStateException => Conflict
        }
    }
  }


}
