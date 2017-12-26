package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class EchoController1 extends Controller {

  def echo1: Action[AnyContent] = Action { request =>
    Ok(s"reply $request")
  }

  def echo1a: Action[AnyContent] = Action { request =>
    val message = request.queryString("message").head
    Ok(s"message = $message")
  }

  def echo2: Action[AnyContent] = Action { request =>
    val body: AnyContent                          = request.body
    val formOpt: Option[Map[String, Seq[String]]] = body.asFormUrlEncoded
    val message: String                           = formOpt.fold("")(_("message").head)
    Ok(s"message = $message")
  }

  def echo3: Action[AnyContent] = Action {
    Ok("Hi")
  }
}
