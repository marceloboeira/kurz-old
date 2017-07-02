package com.kurz.modules

import com.kurz.services.{RedisUrlService, UrlService}
import com.twitter.inject.TwitterModule

object ServicesModule extends TwitterModule {
  override def configure: Unit = {
    bind[UrlService].to[RedisUrlService]
  }
}