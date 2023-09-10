package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.messages.types.DataTable;
import test.UserTest;
import utilities.Context;
import utilities.ScenarioContext;

import java.util.List;
import java.util.Map;

public class stepDefs {

    UserTest ut = new UserTest();
    ScenarioContext SC = new ScenarioContext();

    @Given("Generate the userPayload")
    public void generate_the_user_payload() {
        ut.generatePayload();

    }

    @Given("Test Post user with paylaod generated above and validate for 200 statuscode")
    public void test_post_user_with_paylaod_generated_above_and_validate_for_statuscode() {
        ut.testPostUser();
    }


    @Given("Test GetUser with UserName and save the response in the resource folder as {string}")
    public void testGetUserWithUserNameAndSaveTheResponseInTheResourceFolderAs(String saveInthisFile) {

       String response = ut.testGetUserByUserName(saveInthisFile);
        SC.setContext(Context.ResponseAsString, response);
    }

    @Then("Validate the schema")
    public void validate_the_schema(List<Map<String,String>> table) {
        for(Map<String,String> fileName:table) {
            ut.ValidatSchema(fileName.get("ScehemaFileName"),SC.getContext(Context.ResponseAsString).toString());
        }
    }

    @Given("Update the firstName of the above request payload in first scenario as mentioned below")
    public void update_the_first_name_of_the_above_request_payload_in_first_scenario_as_mentioned_below() {
      ut.UpdateUserNameFromRequest();
    }

    @Then("Test PUT user with the requestpayload from above step")
    public void test_put_user_with_the_requestpayload_from_above_step() {
        ut.testUpdateUserByName();
    }

    @And("Test Get User again to check if he firstName is updated and save the response in the resource folder as {string}")
    public void testGetUserAgainToCheckIfHeFirstNameIsUpdatedAndSaveTheResponseInTheResourceFolderAs(String saveInthisFile) {
        String response = ut.testGetUserByUserName(saveInthisFile);
        SC.setContext(Context.ResponseAsString, response);
    }

    @Given("Test Post user with paylaod as ArrayList generated above and validate for {int} statuscode")
    public void test_post_user_with_paylaod_as_array_list_generated_above_and_validate_for_statuscode(Integer int1) {
        ut.testPostUserwithArrayList();
    }

    @Given("Test Get user Login with username and password of the request payload generated in previous scenario")
    public void test_get_user_login_with_username_and_password_of_the_request_payload_generated_in_previous_scenario() {
        ut.testUserLoginwithQueryParam();
    }

    @Given("Test Deleteuser by Name")
    public void test_deleteuser_by_name() {
        ut.testDeleteByName();

    }



}
