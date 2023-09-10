package endpoints;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import payload.Userpetstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;

public class userEndpointOperations {

    public static Response createUser(Userpetstore payload) {


        /// tried adding the headers using hashmap
        Map<String, String> headers = new HashMap();
        headers.put("Content-type", "application/json;charset=utf-8");
        headers.put("Accept", "application/json");


        Response res = given()
                .headers(headers)
                .body(payload)
                .when()
                .post(urls.petpostuserurl);

        return res;

    }

    public static Response getUser(String userName) {

        Response res = given()
                .pathParam("username", userName)
                .when()
                .get(urls.petgetuserurl);

        return res;

    }

    public static Response updateUser(String userName, Userpetstore payload) {

        Response res = given()
                .pathParam("username", userName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(urls.petupdateuserurl);
        return res;

    }

    public static Response deleteUser(String userName) {

        Response res = given()
                .pathParam("username", userName)
                .accept(ContentType.JSON)
                .when()
                .delete(urls.petdeleteuserurl);

        return res;

    }

    public static Response createUserwithArraylist(ArrayList payload) {


        /// tried adding the headers using hashmap
        Map<String, String> headers = new HashMap();
        headers.put("Content-type", "application/json;charset=utf-8");
        headers.put("Accept", "application/json");


        Response res = given()
                .headers(headers)
                .body(payload)
                .when()
                .post(urls.petpostuserurlwitharraylist);

        return res;

    }


    public static Response getUserLogin(String userName, String password) {

        Response res = given()
                .queryParam("username", userName)
                .queryParam("password",password)
                .when()
                .get(urls.petgetuserloginurl);

        return res;

    }

}
