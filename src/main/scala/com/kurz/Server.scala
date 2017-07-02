package com.kurz

import com.kurz.controllers.MainController
import com.kurz.modules.{KurzModule, ServicesModule}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object Application extends Server

class Server extends HttpServer {
  override def modules = Seq(ServicesModule, KurzModule)

  override protected def configureHttp(router: HttpRouter) {
    router.add(new MainController)
  }
}
