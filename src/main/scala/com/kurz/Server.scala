package com.kurz

import com.kurz.services.RedisUrlService
import com.twitter.server.TwitterServer
import com.twitter.util.{Await, Duration, Future}
import com.twitter.finagle.Http
import com.twitter.finagle.http.Status
import io.finch._

object Server extends TwitterServer {
  val port = flag("http.port", "5000", "HTTP Server port")
  val urlService = new RedisUrlService

  val redirection: Endpoint[Unit] = get(string) { slug: String =>
    Future.value(
      urlService.find(slug) match {
        case Some(url) => Output.unit(Status.TemporaryRedirect).withHeader("Location" -> url)
        case _ => NotFound(new Exception("$slug is not a valid slug"))
      }
    )
  }

  def main(): Unit = {
    val server = Http.server
      .configured(Stats(statsReceiver))
      .serve(s":${port()}", (redirection).toService)

    onExit {
      server.close()
    }

    Await.ready(adminHttpServer)
  }
}
