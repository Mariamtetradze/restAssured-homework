
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;


public class RestAssuredBasics2 {
    @Test
    public void validateLastRecordNameValue() {
        RestAssured.given()
                .when()
                .get("https://chercher.tech/sample/api/product/read ")
                .then()
                .body("records.name[-1]", equalTo("CreateRecord"));
    }

    @Test
    public void postNewTwoUsers() {

        JSONObject request = new JSONObject();
        request.put( "name", "testName");
        request.put( "id","3888");

        given().
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users ").
                then().log().ifStatusCodeIsEqualTo(201);

    }
    @Test
    public void recordDateLessThanCurrent() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

         RestAssured.given()
                 .get("https://chercher.tech/sample/api/product/read ")
                 .then()
                .body("records.created", everyItem(lessThan(formatter.format(date))));
    }
}
