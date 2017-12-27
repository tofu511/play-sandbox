package controllers

import javax.inject._
import play.api._
import play.api.i18n._
import play.api.mvc._
import models.User

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(override val messagesApi: MessagesApi) extends Controller with I18nSupport {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action { implicit request =>
    val userOpt: Option[User] = for {
      userIdString <- request.session.get("userId")
      user         <- User.findById(userIdString.toLong)
    } yield user
    Ok(views.html.index(userOpt))
  }

}
