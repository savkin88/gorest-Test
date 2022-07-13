package in.co.gorest.userinfo;

import in.co.gorest.constant.EndPoints;
import in.co.gorest.model.GoRestPoJo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GorestSteps {
    @Step
    public ValidatableResponse creatUser(String name, String email, String gender, String status) {
        GoRestPoJo goRestPoJo = new GoRestPoJo();
        goRestPoJo.setName(name);
        goRestPoJo.setEmail(email);
        goRestPoJo.setGender(gender);
        goRestPoJo.setStatus(status);
        return SerenityRest.given().log().all()
                .auth().oauth2("291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .body(goRestPoJo)
                .when()
                .post(EndPoints.CREAT_USER)
                .then();

    }
    @Step
    public ValidatableResponse getSingleUser(int userId){
        return SerenityRest.given().log().all()
                .auth().oauth2("291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .pathParam("userID",userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
    @Step
    public ValidatableResponse updateUser(int userId,String name, String email,String gender, String status) {
        GoRestPoJo goRestPoJo = new GoRestPoJo();
        goRestPoJo.setName(name);
        goRestPoJo.setEmail(email);
        goRestPoJo.setStatus(status);
        goRestPoJo.setGender(gender);

        return SerenityRest.given().log().all()
                .auth().oauth2("291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .pathParam("userID",userId)
                .body(goRestPoJo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();

    }
    @Step
    public ValidatableResponse deleteSingleUser(int userId){
        return SerenityRest.given().log().all()
                .auth().oauth2("291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 291904970ca833dadf57d74c5eb1af568a47ad82f963a134e0620176673e189e")
                .pathParam("userID",userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
}