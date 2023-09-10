package test;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import endpoints.userEndpointOperations;
import org.junit.Assert;
import payload.Userpetstore;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.FileUtil.createFileWithContent;

public class UserTest {

   public static Faker randomDataGen;
   public static Userpetstore userPayload;



    public void generatePayload(){
        randomDataGen = new Faker();

        userPayload = new Userpetstore();
        userPayload.setId(randomDataGen.idNumber().hashCode());
        userPayload.setUsername(randomDataGen.name().username());
        userPayload.setFirstName(randomDataGen.name().firstName());
        userPayload.setLastName(randomDataGen.name().lastName());
        userPayload.setEmail(randomDataGen.internet().safeEmailAddress());
        userPayload.setPassword(randomDataGen.internet().password(5,10));
        userPayload.setPhone(randomDataGen.phoneNumber().cellPhone());

    }


    public void testPostUser(){
       Response res = userEndpointOperations.createUser(this.userPayload);

       if(res.statusCode() == 200){
           System.out.println("POST Request executed Successfully");
       }
       else{
           System.out.println("POST Request execution Failed");
       }

     //   Assert.assertEquals(res.getStatusCode(),200);
    }


    public String testGetUserByUserName(String saveInthisFile){
        Response res = userEndpointOperations.getUser(this.userPayload.getUsername());
        System.out.println(res.asString());
        if(res.statusCode() == 200){
            System.out.println("GET Request executed Successfully");
        }
        else{
            System.out.println("GET Request execution Failed");
        }
        createFileWithContent(saveInthisFile, res.asString());

       // Validate f the userName of the payload and the response is same using the JsonPath
      if(userPayload.getFirstName().equals(JsonPath.read(res.asString(),"$.firstName").toString())){
          System.out.println("Created user FirstName matches with Paylaod FirstName");
      }
      else{
          System.out.println("Created user FirstName does not matche with Paylaod FirstName");
      }

        return res.asString();

    }


    public void UpdateUserNameFromRequest() {

        //update the firstname, generate a random value using the below statement on the payload
        userPayload.setFirstName(randomDataGen.name().firstName());

    }

    public void testUpdateUserByName(){
        Response res = userEndpointOperations.updateUser(this.userPayload.getUsername(), userPayload);
        if(res.statusCode() == 200){
            System.out.println("PUT Request executed Successfully");
        }
        else{
            System.out.println("PUT Request execution Failed");
        }


    }


    public void testPostUserwithArrayList(){
        ArrayList<Object> jsonArrayPayload = new ArrayList<>();
        userPayload.setId(randomDataGen.idNumber().hashCode());
        jsonArrayPayload.add(userPayload);
        Response res = userEndpointOperations.createUserwithArraylist(jsonArrayPayload);
        if(res.statusCode() == 200){
            System.out.println("POST request with Arraylist payload  executed Successfully");
        }
        else{
            System.out.println("POST request with Arraylist payload  execution Failed");
        }

    }


    public void testUserLoginwithQueryParam(){
        Response res = userEndpointOperations.getUserLogin(userPayload.getUsername(),userPayload.getPassword());
        if(res.statusCode() == 200){
            System.out.println("Get Request with queryparams executed Successfully");
        }
        else{
            System.out.println("Get Request with queryparams execution Failed");
        }

    }


    public void testDeleteByName(){
        Response res = userEndpointOperations.deleteUser(this.userPayload.getUsername());
        if(res.statusCode() == 200){
            System.out.println("Delete Request executed Successfully");
        }
        else{
            System.out.println("Delete Request execution Failed");
        }


    }


    public  void ValidatSchema(String schemaFileName, String res){

        File schemaFile = new File(System.getProperty("user.dir")+"/src/test/resources/testData/schema/"+schemaFileName);
        boolean bFlag = false;
        try{
            Assert.assertThat(res, JsonSchemaValidator.matchesJsonSchema(schemaFile));
            System.out.println("Scehma Validation is Successful");

        }catch(Exception e){
            bFlag = true;
            System.out.println("Scehma Validation Failed");
        }
    }

}
