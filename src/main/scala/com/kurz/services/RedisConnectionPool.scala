package com.kurz.services

import java.net.URI

import redis.clients.jedis.{JedisPool, JedisPoolConfig}

object RedisConnectionPool {
  lazy val instance: JedisPool = {
    new JedisPool(poolConfig, connectionURI, 10000)
  }

  lazy val poolConfig: JedisPoolConfig = {
    val config = new JedisPoolConfig()

    // Use ENV to set MAX/MIN/IDLE?
    config.setMaxTotal(100)
    config.setMinIdle(100)
    config.setMaxIdle(100)
    config.setMaxWaitMillis(10000)

    config
  }

  lazy val connectionURI: URI = {
    new URI(Option(System.getenv("REDIS_URL")).getOrElse("redis://127.0.0.1:6379"))
  }
}