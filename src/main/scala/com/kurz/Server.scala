package com.kurz
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.kurz.controllers.RootController

object Application extends Server

class Server extends HttpServer {
  override val disableAdminHttpServer = true

  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add(new RootController)
  }
}
