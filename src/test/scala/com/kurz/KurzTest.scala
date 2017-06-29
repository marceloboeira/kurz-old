package com.kurz

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest

class KurzTest extends FeatureTest {
  override val server: EmbeddedHttpServer = new EmbeddedHttpServer(twitterServer = new Server)

  test("Returns 200 for '/'") {
    server.httpGet(
      path = "/",
      andExpect = Status.Ok,
      withBody = "Hallo Welt"
    )
  }
}
