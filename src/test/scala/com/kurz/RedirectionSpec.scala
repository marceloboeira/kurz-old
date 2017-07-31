package com.kurz

import com.kurz.services.RedisConnectionPool
import com.twitter.finagle.http.Status
import io.finch.Input
import org.scalatest.{BeforeAndAfter, FunSpec, Matchers}

class RedirectionSpec extends FunSpec with Matchers with BeforeAndAfter {
  import com.kurz.Server.redirection

  before {
    RedisConnectionPool.instance.getResource().del("mb")
    RedisConnectionPool.instance.getResource().set("mb", "http://marceloboeira.com")
  }

  describe ("redirection") {
    describe("when the slug exists") {
      it("returns with the temporary redirection status") {
        redirection(Input.get("/mb")).awaitOutputUnsafe().map(_.status) shouldBe Some(Status.TemporaryRedirect)
      }

      it("returns with the proper header for Location") {
        redirection(Input.get("/mb")).awaitOutputUnsafe().map(_.headers) shouldBe Some(Map("Location" -> "http://marceloboeira.com"))
      }
    }

    describe("when the slug does not exist") {
      it("returns with the not found status") {
        redirection(Input.get("/invalid")).awaitOutputUnsafe().map(_.status) shouldBe Some(Status.NotFound)
      }
    }
  }
}
