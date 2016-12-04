package model.users

import javax.inject.{Inject, Singleton}

import model.MongoRepo
import model.util.Selector
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.collections.bson.BSONCollection
import play.api.libs.concurrent.Execution.Implicits._

@Singleton
class UserRepo @Inject() (override val mongoApi: ReactiveMongoApi) extends MongoRepo {

  val collection = mongoApi.database.map(_.collection[BSONCollection]("user"))

  def findByLogin(login: String) = {
    collection.flatMap(_.find(Selector.eq("login", login)).one[User])
  }

  def saveUser(user: User) = {
    collection.flatMap(_.insert(user))
  }

}
