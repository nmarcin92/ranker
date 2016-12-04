package model.users

import reactivemongo.bson.{BSONDocumentReader, Macros}

case class User(username: String, login: String, passwordHash: String)

object User {
  implicit val handler = Macros.handler[User]
}
