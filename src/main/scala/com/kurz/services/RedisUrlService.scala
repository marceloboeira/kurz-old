package com.kurz.services

import com.kurz.modules.JedisPoolInstance
import redis.clients.jedis.Jedis

object RedisUrlService

class RedisUrlService extends UrlService {
  def find(slug: String): Option[String] = {
    val client = JedisPoolInstance.instance.getResource()
    try {
      Option(client.get(slug))
    }
    finally {
      client.close
    }
  }
}