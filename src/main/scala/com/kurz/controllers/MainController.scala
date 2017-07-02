package com.kurz.controllers

import com.kurz.services.RedisUrlService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.Future

class MainController extends Controller {
  val urlService = new RedisUrlService()

  get("/:slug") { request: Request =>
    urlService.find(request.params("slug")) match {
      case Some(url) => response.temporaryRedirect.header("Location", url)
      case None => response.notFound
    }
  }
}
