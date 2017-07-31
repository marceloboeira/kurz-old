# :rocket: Kurz [![Build Status](https://travis-ci.org/marceloboeira/kurz.svg?branch=master)](https://travis-ci.org/marceloboeira/kurz)
> A blazing fast URL Shortner

## About

### Motivation

This is my first project with Scala, so I have decided to implement something dummy, but useful.
PS: I am still exploring the language, the frameworks, so, this is crappy code, don't judge me.

### Name

**Kurz** - it is the literal translation of **short** to German. [[more](http://www.dict.cc/deutsch-englisch/kurz.html)]

## How fast?

Check the [PERFORMANCE.md](https://github.com/marceloboeira/kurz/blob/master/PERFORMANCE.md) file for complete information.

## Enpoints
> List of endpoints and parameters

### Redirection

The root path is the default redirection path.

`GET /:slug` -> where slug is the short url or alias.

The client will be redirected to the full URL using a [Temporary Redirect](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.8), since it is not cached by the browser.
