name := "com/kurz"
version := "1.0"

scalaVersion := "2.12.1"

lazy val versions = new {
  val finch = "0.15.1"
  val circe = "0.8.0"
  val logback = "1.1.7"
  val redis = "2.7.2"
  val scalaTest = "3.0.0"
  val twitterServer = "1.30.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-generic" % versions.circe,

  "com.github.finagle" %% "finch-core" % versions.finch,
  "com.github.finagle" %% "finch-generic" % versions.finch,
  "com.github.finagle" %% "finch-circe" % versions.finch,

  "com.twitter" %% "twitter-server" % versions.twitterServer,
  "ch.qos.logback" % "logback-classic" % versions.logback,

  "redis.clients" % "jedis" % versions.redis,

  "org.scalatest" %% "scalatest" % versions.scalaTest % "test"
)

enablePlugins(JavaAppPackaging)
