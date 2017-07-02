# Kurz
> A URL Shortner

## Redirection

The root path is the default redirection path.

`GET /:slug` -> where slug is the short url or alias.

The client will be redirected to the full URL using a [Temporary Redirect](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.3), since it is not cached by the browser.
