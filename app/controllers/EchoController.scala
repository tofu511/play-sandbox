package controllers

import javax.inject._

import play.api.mvc._
import play.api.i18n._

@Singleton
class EchoController @Inject()(override val messagesApi: MessagesApi) extends Controller with I18nSupport {

//  def echo: Action[AnyContent] = Action { implicit request =>
//    val message = request.queryString("message").head
//    Ok(views.html.echo(message))
//  }
}
