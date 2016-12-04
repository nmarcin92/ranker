package controllers.auth

import play.api.libs.json.Json

case class UserAuthInfo(username: String, login: String)

object UserAuthInfo {
  implicit val userAuthFormat = Json.format[UserAuthInfo]
}

