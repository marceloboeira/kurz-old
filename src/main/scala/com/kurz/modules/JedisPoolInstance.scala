package com.kurz.modules

import java.net.URI

import com.google.inject.{Provides, Singleton}
import com.twitter.inject.TwitterModule
import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

object JedisPoolInstance extends TwitterModule {
  lazy val instance: JedisPool = {
    val poolConfig = new JedisPoolConfig()
    poolConfig.setMaxTotal(10)
    poolConfig.setMinIdle(10)
    poolConfig.setMaxIdle(10)
    poolConfig.setMaxWaitMillis(10000)

    new JedisPool(poolConfig, new URI("redis://127.0.0.1:5000"), 10000)
  }
}