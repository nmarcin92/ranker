package model.util

import reactivemongo.bson.{BSONDocument, BSONHandler, BSONString, BSONValue}

object Selector {

  def eq[T](key: String, value: T)(implicit handler: BSONHandler[_ <: BSONValue, T]) = {
    BSONDocument(key -> BSONDocument("$eq" -> value))
  }

}

