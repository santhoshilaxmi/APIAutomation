package test;

import com.github.javafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import endpoints.userEndpointOperations;
import org.junit.Assert;
import payload.Userpetstore;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

     //   Assert.assertEquals(res.getStatusCode(),200);
    }


    public String testGetUserByUserName(String saveInthisFile){
        Response res = userEndpointOperations.getUser(this.userPayload.getUsername());
        System.out.println(res.asString());
        createFileWithContent(saveInthisFile, res.asString());

        return res.asString();
     //   Assert.assertEquals(res.getStatusCode(),200);
    }


    public void UpdateUserNameFromRequest() {

        //update the firstname, generate a random value using the below statement on the payload
        userPayload.setFirstName(randomDataGen.name().firstName());

    }

    public void testUpdateUserByName(){
        Response res = userEndpointOperations.updateUser(this.userPayload.getUsername(), userPayload);
        //Assert.assertEquals(res.getStatusCode(),200);

    }


    public void testPostUserwithArrayList(){
        ArrayList<Object> jsonArrayPayload = new ArrayList<>();
        userPayload.setId(randomDataGen.idNumber().hashCode());
        jsonArrayPayload.add(userPayload);
        Response res = userEndpointOperations.createUserwithArraylist(jsonArrayPayload);
        //Assert.assertEquals(res.getStatusCode(),200);
    }


    public void testUserLoginwithQueryParam(){
        Response res = userEndpointOperations.getUserLogin(userPayload.getUsername(),userPayload.getPassword());
       // Assert.assertEquals(res.getStatusCode(),200);
    }


    public void testDeleteByName(){
        Response res = userEndpointOperations.deleteUser(this.userPayload.getUsername());
       // Assert.assertEquals(res.getStatusCode(),200);
    }


    public  void ValidatSchema(String schemaFileName, String res){

        File schemaFile = new File(System.getProperty("user.dir")+"/src/test/resources/testData/schema/"+schemaFileName);
        boolean bFlag = false;
        try{
            Assert.assertThat(res, JsonSchemaValidator.matchesJsonSchema(schemaFile));

        }catch(Exception e){
            bFlag = true;
            System.out.println("Scehma Validation Failed");
        }
    }

}
