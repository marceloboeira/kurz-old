package com.kurz.services

class RedisUrlService extends UrlService {
  def find(slug: String): Option[String] = {
    val client = RedisConnectionPool.instance.getResource()
    try {
      Option(client.get(slug))
    }
    finally {
      client.close
    }
  }
}