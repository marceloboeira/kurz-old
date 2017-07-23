package com.kurz

import com.kurz.services.RedisConnectionPool
import com.twitter.finagle.http.Status
import io.finch.Input
import org.scalatest.{FunSpec, Matchers}

class RedirectionSpec extends FunSpec with Matchers {
  import com.kurz.Server.redirection

  describe ("redirection") {
    describe("when the slug exists") {
      it("redirects to the expected URL") {
        RedisConnectionPool.instance.getResource().set("mb", "http://marceloboeira.com")
        redirection(Input.get("/mb")).awaitOutputUnsafe().map(_.status) shouldBe Some(Status.TemporaryRedirect)
      }
    }

    describe("when the slug does not exist") {
      it("returns not found") {
        redirection(Input.get("/foo")).awaitOutputUnsafe().map(_.status) shouldBe Some(Status.NotFound)
      }
    }
  }
}
