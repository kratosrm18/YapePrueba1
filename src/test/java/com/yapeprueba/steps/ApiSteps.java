package com.yapeprueba.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSteps {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private Response response;
    private RequestSpecification request;
    private String endPoint = "";
    private JSONObject data = new JSONObject();
    private String token = "";

    @Given("Configuro la solicitud para el endpoint auth")
    public void configurar_enbdpoint_auth() {
        RestAssured.baseURI = BASE_URL;
        endPoint = "/auth";
        data.put("username","admin");
        data.put("password","password123");
        request = RestAssured.given()
                .contentType("application/json")
                .body(data.toString());
    }

    @When("Envio el request metodo {}")
    public void enviar_request_con_metodo(String metodo) {
        switch (metodo) {
            case "GET":
                response = request.get(endPoint);
                break;
            case "POST":
                response = request.post(endPoint);
                break;
            case "PUT":
                response = request.put(endPoint);
                break;
            case "DELETE":
                response = request.delete(endPoint);
                break;
        }

    }

    @Then("Recibo la respuesta con codigo {int}")
    public void se_recibe_codigo(int codigo) {
        Assert.assertEquals("Se reicibio el codigo "+response.getStatusCode(),codigo, response.getStatusCode());
    }

    @Then("Se recibio el token")
    public void se_recibe_token() {
        token = response.jsonPath().getString("token");
        System.out.println(token);
        Assert.assertNotNull("No se recibio el token", token);
        Assert.assertTrue("Se recibio un token vacio",token.length()!=0);
    }

    @Given("Configuro la solicitud para el endpoint GetBookingIds")
    public void configurar_endpoint_getBookingIDs() {
        configurar_enbdpoint_auth();
        enviar_request_con_metodo("POST");
        RestAssured.baseURI = BASE_URL;
        endPoint = "/booking";
        request = RestAssured.given()
                .contentType("application/json")
                .cookie("token="+token);
    }

    @And("Recibo los IDs de booking")
    public void reciboLosIDsDeBooking() {
        List<Map<String, Object>> bookingIDs = response.jsonPath().getList("");
        Assert.assertNotNull(bookingIDs);
        Assert.assertTrue(bookingIDs.size() > 0);
        System.out.println(bookingIDs);
    }

    @Given("Configuro la solicitud para el endpoint HealthCheck")
    public void configurar_endpoint_healthCheck() {
        configurar_enbdpoint_auth();
        enviar_request_con_metodo("POST");
        RestAssured.baseURI = BASE_URL;
        endPoint = "/ping";
        request = RestAssured.given()
                .contentType("application/json");
    }
}
