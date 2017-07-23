package com.kurz

import com.kurz.services.RedisUrlService
import com.twitter.server.TwitterServer
import com.twitter.finagle.param.Stats
import com.twitter.util.{Await, Duration}
import com.twitter.finagle.Http
import com.twitter.finagle.http.Status
import io.finch._

object Server extends TwitterServer {
  val redirection: Endpoint[Unit] = get(string) { slug: String =>
    val urlService = new RedisUrlService

    urlService.find(slug) match {
      case Some(url) => Output.unit(Status.TemporaryRedirect).withHeader("Location" -> url)
      case _ => NotFound(new Exception("$slug is not a valid slug"))
    }
  }

  def main(): Unit = {
    val server = Http.server
      .configured(Stats(statsReceiver))
      .serve(":8081", (redirection).toService)

    onExit {
      server.close()
    }

    Await.ready(adminHttpServer)
  }
}
