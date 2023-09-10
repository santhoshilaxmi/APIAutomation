# APIAutomation
Framework created using maven(3.9.4) and RestAssured (5.3.0),JAVA(17) (Tried to use latest versions ☺)

UI targeted to test - https://petstore.swagger.io/#/user/

was unable to generate Auth token for the swagger which was shared to me for testing, so considered online swagger available for test 

Tested the User module from PetStore

Did not generate report extent report, generated a cucumber-reports

As part of this Framework tried to cover below 
1.	Create a feature file (Scenarios covering POST, GET, PUT, DELETE, SchemaValidation, JsonPath Validation, saving response in a file)
2.	Created a StepDef File
3.	Created a UserTest which holds tests for all the UserOperations
4.	Also cretead a FileUtil, and used ScenarioContext concept
5.	Created a class for saving URL's and also the hold the CURD operations 
6.	Created a getter setter methods class for generating USER payload 
7.	Added a Runner file

**IDE Console**

![image](https://github.com/santhoshilaxmi/APIAutomation/assets/38223932/e49abaeb-2e0c-476f-bdc2-d87dac36e58c)
