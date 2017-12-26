package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class EchoController2 extends Controller {

//  def echo: Action[AnyContent] = Action { request =>
//    val message = request.queryString("message").head
//    Ok(views.html.index(message))
//
//  }
}
