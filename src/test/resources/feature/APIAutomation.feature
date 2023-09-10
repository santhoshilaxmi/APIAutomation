@APIAutomation

Feature: API Automation  of PetStore User
  Description: Swagger = "https://petstore.swagger.io/#/user"


# configure the browser here
  Scenario:Generate the Payload using the Getter and Setter Approach
    Given Generate the userPayload

# Scenario 1 Click on the Questionaire link and fill the details in the first page
  Scenario: Test Post User
    Given Test Post user with paylaod generated above and validate for 200 statuscode


# Scenario 2 Come to home page
  Scenario: Test GetUserByUserName
    Given Test GetUser with UserName and save the response in the resource folder as "GetUserByID.json" and Validate the FirstName
    Then Validate the schema
      | ScehemaFileName  |
      | usersSchema.json |


# Scenario 3 click on detailed reports and then click on DownloadCSV
  Scenario: testUpdateUserByName
    Given Update the firstName of the above request payload in first scenario as mentioned below
    Then Test PUT user with the requestpayload from above step
    And Test Get User again to check if he firstName is updated and save the response in the resource folder as "GetUserByIDafterUpdate.json"

  Scenario: Test Post User with payload in the form of ArrayList
    Given Test Post user with paylaod as ArrayList generated above and validate for 200 statuscode

  Scenario: Test Get User Login with Query params
    Given Test Get user Login with username and password of the request payload generated in previous scenario

  Scenario: Test Delete User by UserName
    Given Test Deleteuser by Name
