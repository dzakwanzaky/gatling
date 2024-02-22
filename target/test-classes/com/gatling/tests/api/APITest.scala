package com.gatling.tests.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class APITest extends Simulation{
  val httpProtocol = http
    .baseUrl("https://reqres.in/")

  val scn = scenario("Get User by User ID")
    .exec(
      http("Get user")
        .get("api/users?page=2")
        .check(
          status.is(200)
        )
    )

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol),
  )
}
