package com.gatling.tests.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class test extends Simulation{

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

  val scn2 = scenario("Create User")
    .exec(
      http("POST User")
        .post("/users")
        .asJson
        .body(StringBody(
          """
            |{
            |  "DisplayName": "Name 55",
            |  "Email": "name55@gmail.com",
            |  "ID": 55
            |}
            |""".stripMargin))
        .check(
          status.is(201)
        )
    )

  val scn3 = scenario("Update User")
    .exec(
      http("PUT User")
        .put("/users/1")
        .asJson
        .body(StringBody(
          """
            |{
            |  "DisplayName": "Name Edit"
            |}
            |""".stripMargin))
        .check(
          status.is(204)
        )
    )

  val scn4 = scenario("Delete User")
    .exec(
      http("DELETE User")
        .delete("/users/4")
        .asJson
        .check(
          status.is(204)
        )
    )

  setUp(
    scn.inject(atOnceUsers(1))
      .protocols(httpProtocol),
    scn2.inject(atOnceUsers(1))
      .protocols(httpProtocol),
    scn3.inject(atOnceUsers(1))
      .protocols(httpProtocol),
    scn4.inject(atOnceUsers(1))
      .protocols(httpProtocol)
  )
}
