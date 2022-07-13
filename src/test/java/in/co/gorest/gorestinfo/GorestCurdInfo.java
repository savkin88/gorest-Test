package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import in.co.gorest.userinfo.GorestSteps;
import in.co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class GorestCurdInfo extends TestBase {
    static String name ="kiya";
    static String email="kiya"+ TestUtils.getRandomValue()+"@gmail.com";
    static String gender="female";
    static String status="active";
    static int userId;
    @Steps
    GorestSteps gorestSteps ;
    @Title("create user")
    @Test
    public void test001(){
        ValidatableResponse response= gorestSteps.creatUser(name,email,gender,status);
        response.log().all().statusCode(201);
            userId=response.extract().path("id");
        System.out.println(userId);

    }
    @Title("get single user")
    @Test
    public void test002(){
        ValidatableResponse response= gorestSteps.getSingleUser(userId);
        response.log().all().statusCode(200);
        response.body("name",equalTo(name));
    }
    @Title("update user ")
    @Test
    public void test003(){
        name=name+"updated001";
        ValidatableResponse response= gorestSteps.updateUser(userId,name,email,gender,status);
        response.log().all().statusCode(200);
    }
    @Title("delete single user")
    @Test
    public void test004(){
        ValidatableResponse response= gorestSteps.deleteSingleUser(userId);
        response.log().all().statusCode(204);

        ValidatableResponse response1= gorestSteps.getSingleUser(userId);
        response1.log().all().statusCode(404);
    }
}
