.PHONY: start
start:
	target/universal/stage/bin/com-kurz -- -http.port=:$(PORT)

.PHONY: test
test:
	sbt test

.PHONY: deploy
deploy:
	git push heroku master

.PHONY: build
build:
	sbt compile stage

.PHONY: start_dev
start_dev: build
	PORT=5000 heroku local
