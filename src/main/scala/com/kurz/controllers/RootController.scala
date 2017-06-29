package com.kurz.controllers
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class RootController extends Controller {
  get("/") { request: Request =>
    "Hallo Welt"
  }
}
