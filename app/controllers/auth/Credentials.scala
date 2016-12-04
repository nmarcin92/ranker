package controllers.auth

import play.api.libs.json.Json

case class UserCredentials(login: String, password: String)

case class UserRegistration(credentials: UserCredentials, username: String)

object UserCredentials {
  implicit val userCredentialsFormat = Json.format[UserCredentials]
}

object UserRegistration {
  implicit val userRegistrationFormat = Json.format[UserRegistration]
}
