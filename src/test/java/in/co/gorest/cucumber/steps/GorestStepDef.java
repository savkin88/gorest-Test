package in.co.gorest.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.co.gorest.userinfo.GorestSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class GorestStepDef {
    static String name ="kiya";
    static String email="kiya"+ TestUtils.getRandomValue()+"@gmail.com";
    static String gender="female";
    static String status="active";
    static int userId;
    ValidatableResponse response;
    @Steps
    GorestSteps gorestSteps ;
    @When("^I send post request to creat data$")
    public void iSendPostRequestToCreatData() {
    }

    @And("^I should insert name,email,gender,status$")
    public void iShouldInsertNameEmailGenderStatus() {
        response= gorestSteps.creatUser(name,email,gender,status);
    }

    @Then("^User should be created successfully$")
    public void userShouldBeCreatedSuccessfully() {
        response.log().all().statusCode(201);

    }

    @And("^User Id should be created$")
    public void userIdShouldBeCreated() {
        userId=response.extract().path("id");
    }

    @When("^I send get request to fatch data by given Id$")
    public void iSendGetRequestToFatchDataByGivenId() {
        response= gorestSteps.getSingleUser(userId);
    }

    @And("^I should be able to get user data succefully$")
    public void iShouldBeAbleToGetUserDataSuccefully() {
        response.log().all().statusCode(200);
        response.body("name",equalTo(name));
    }

    @When("^I send Patch request to update data$")
    public void iSendPatchRequestToUpdateData() {
       ;
    }

    @And("^I update name,email,gender,status$")
    public void iUpdateNameEmailGenderStatus() {
        name=name+"updated001";
        response= gorestSteps.updateUser(userId,name,email,gender,status);
    }

    @Then("^I should be able to update user successfuly$")
    public void iShouldBeAbleToUpdateUserSuccessfuly() {
        response.log().all().statusCode(200);
    }

    @When("^I send delete request$")
    public void iSendDeleteRequest() {
        response= gorestSteps.deleteSingleUser(userId);
    }

    @Then("^I should be able to delete record successfully$")
    public void iShouldBeAbleToDeleteRecordSuccessfully() {
        response.log().all().statusCode(204);
    }

    @And("^to verify record has been deleted$")
    public void toVerifyRecordHasBeenDeleted() {
        response= gorestSteps.getSingleUser(userId);
        response.log().all().statusCode(404);
    }
}
