# :rocket: Kurz [![Build Status](https://travis-ci.org/marceloboeira/kurz.svg?branch=master)](https://travis-ci.org/marceloboeira/kurz)
> A URL Shortner

## Redirection

The root path is the default redirection path.

`GET /:slug` -> where slug is the short url or alias.

The client will be redirected to the full URL using a [Temporary Redirect](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.3), since it is not cached by the browser.


## How fast?

Data collected by running [Apache Benchmark](https://httpd.apache.org/docs/2.4/programs/ab.html) against my MacBook Pro / Intel Core i5@2.6 GHz / 8GB RAM. Redis running locally, java/jvm with the default setup.

### Scenario

The target is the redirection URL, which probably, would be the one suffering from high pressure on such system. Given a single entry on Redis, which is also running on the local machine. The endpoint always gets the redirection URL from redis and returns a 307 with the location header.

### First round (cold start)
> 1k requests with concurrency varying from 1 to 500 (no warmup).

`ab -n 1000 -c ${1-500} http://127.0.0.1:5000/foo`

No warm up, like a new node on a cluster. With 1 and 10 concurrent connections we do not notice the warmup spike, perhaps because the scale is not good enough. However, it is somewhat noticeable for 50, 100 concurrent connections and very noticeable for 500 concurrent.

Highlight: for all the tests the repsonse time spike is ~75ms with 500 concurrent requests, which is a very reasonable result considering the scenario foa new node, which tends to improve after warming up.

![First Round](https://github.com/marceloboeira/kurz/blob/master/docs/benchmark-1k-requests.png?raw=true)

### Second round (steady load)
> 10k requests with concurrency varying from 1 to 50 (warmed up)

`ab -n 10000 -c ${1-50} http://127.0.0.1:5000/foo`

Very powerful throughput after warming up, considering 1 and 10 concurrent requests, the average response time is less than 2ms.
Given 50 concurrent requests it managed to stay quite stable, with an average of ~6ms response time, and a spike of 14ms.

![Second Round](https://github.com/marceloboeira/kurz/blob/master/docs/benchmark-10k-requests-low-stress.png?raw=true)

### Third round (high load)
> 10k requests with concurrency varying from 1 to 200 (warmed up)

`ab -n 10000 -c ${1-200} http://127.0.0.1:5000/foo`

Same test as before, but now including 100 and 200 concurrent requests. Still quite impressive, keeping it under ~40ms during almost all the test.

![Third Round](https://github.com/marceloboeira/kurz/blob/master/docs/benchmark-10k-requests-medium-stress.png?raw=true)
