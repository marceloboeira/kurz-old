package com.kurz.services

trait UrlService {
  def find(slug: String): Option[String]
}
