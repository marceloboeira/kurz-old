package com.kurz.services

import java.net.URI

import redis.clients.jedis.{JedisPool, JedisPoolConfig}

object RedisConnectionPool {
  lazy val instance: JedisPool = {
    val poolConfig = new JedisPoolConfig()

    // Move config to ENV
    poolConfig.setMaxTotal(100)
    poolConfig.setMinIdle(100)
    poolConfig.setMaxIdle(100)
    poolConfig.setMaxWaitMillis(10000)

    new JedisPool(poolConfig, new URI("redis://127.0.0.1:6379"), 10000)
  }
}