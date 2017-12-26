package controllers

import javax.inject._

import models.Message
import play.api.mvc._
import play.api.i18n._

@Singleton
class MessageController @Inject()(override val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    val messageSeq = Seq(Message("greeting", "Hello Scala!", "山田太郎"), Message("挨拶", "こんばんは", "山田"))
    Ok(views.html.message(messageSeq))
  }
}
