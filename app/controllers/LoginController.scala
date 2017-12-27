package controllers

import javax.inject._

import forms.Login
import models.User
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import play.api.i18n._
import play.api.Logger
import scalikejdbc._

@Singleton
class LoginController @Inject()(override val messagesApi: MessagesApi) extends Controller with I18nSupport {

  private val form = Form(
    mapping(
      "email"    -> email,
      "password" -> nonEmptyText
    )(Login.apply)(Login.unapply)
  )

  // ログインページを描画(GET)
  def index: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.login(form))
  }

  // ログインする(POST)
  def login: Action[AnyContent] = Action { implicit request /*: Request[AnyContent] */ =>
    form
      .bindFromRequest()
      .fold(
        { formWithErrors =>
          BadRequest(views.html.login(formWithErrors))
        }, { login =>
          val userOpt = User.findBy(sqls.eq(User.column.email, login.email))
          userOpt.fold(
            BadRequest(views.html.login(form.fill(login).withGlobalError(Messages("notFoundUserError", login.email))))
          ) { user =>
            if (user.password == login.password)
              Redirect(routes.HomeController.index()).withSession("userId" -> user.id.get.toString)
            else
              BadRequest(
                views.html.login(form.fill(login).withGlobalError(Messages("invalidPasswordError", login.email)))
              )
          }
        }
      )
  }

  // ログアウトする(GET)
  def logout = Action { implicit request =>
    Redirect(routes.HomeController.index()).withNewSession
  }
}
