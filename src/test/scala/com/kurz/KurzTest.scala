package com.kurz

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest.FunSpec

class KurzTest extends FunSpec with FeatureTestMixin {
  override val server: EmbeddedHttpServer = new EmbeddedHttpServer(twitterServer = new Server)

  describe("redirection") {
    describe("when the slug exists") {
      it("redirects to the expected URL") {
        server.httpGet(
          path = "/google",
          andExpect = Status.TemporaryRedirect,
          withLocation = "http://google.com"
        )
      }
    }

    describe("when the slug does not exist") {
      it("returns not found") {
        server.httpGet(path = "/invalid", andExpect = Status.NotFound)
      }
    }
  }
}
