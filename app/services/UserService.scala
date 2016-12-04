package services

import javax.inject.{Inject, Singleton}

import controllers.auth.{UserCredentials, UserRegistration}
import model.users.{User, UserRepo}
import org.mindrot.jbcrypt.BCrypt
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

@Singleton
class UserService @Inject()(val userRepo: UserRepo) {

  import UserService._

  def registerUser(registration: UserRegistration): Future[Unit] = {
    userRepo.findByLogin(registration.credentials.login)
        .flatMap(_.map(
          _ => Future.failed(new IllegalStateException()))
          .getOrElse(saveUser(User(registration.username,
            registration.credentials.login, generateHash(registration.credentials.password)))))
  }

  def saveUser(user: User): Future[Unit] = {
    userRepo.saveUser(user)
      .flatMap(result => if (result.hasErrors) Future.failed(new IllegalStateException())
      else Future.successful(Unit))
  }

  def findUserByCredentials(userCredentials: UserCredentials): Future[Option[User]] = {
    userRepo.findByLogin(userCredentials.login)
      .map(_.flatMap(user => if (checkPassword(userCredentials.password, user.passwordHash)) Some(user) else None))
  }

}

object UserService {

  private def generateHash(password: String) = BCrypt.hashpw(password, BCrypt.gensalt())

  private def checkPassword(password: String, pwdHash: String) = BCrypt.checkpw(password, pwdHash)

}
