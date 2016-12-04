package model

import play.modules.reactivemongo.ReactiveMongoApi

/**
  * @author mnowak
  */
trait MongoRepo {
  val mongoApi: ReactiveMongoApi
}
