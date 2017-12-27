package controllers

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class LoginControllerSpec extends PlayFunSpec with GuiceOneAppPerSuite {

  describe("controller spec") {
    describe("LoginController#index") {
      it("should be valid") {
        val loginController = inject[LoginController]
        val result          = loginController.index.apply(addCsrfToken(FakeRequest()))
        status(result) mustBe OK
      }
    }

    describe("LoginController#login") {
      it("should be valid") {
        val loginController = inject[LoginController]
        val result =
          loginController.login.apply(
            addCsrfToken(FakeRequest().withFormUrlEncodedBody("email" -> "test1@test.com", "password" -> "test1"))
          )
        status(result) mustBe SEE_OTHER
      }
    }
  }

  describe("route spec") {
    describe("route of LoginController#index") {
      it("should be valid") {
        val result = route(app, addCsrfToken(FakeRequest(GET, routes.LoginController.index().toString))).get
        status(result) mustBe OK
      }
    }

    describe("route of LoginController#login") {
      it("should be valid") {
        val result = route(app,
                           addCsrfToken(FakeRequest(POST, routes.LoginController.login().toString))
                             .withFormUrlEncodedBody("email" -> "test1@test.com", "password" -> "test1")).get

        status(result) mustBe SEE_OTHER
      }
    }
  }

}
