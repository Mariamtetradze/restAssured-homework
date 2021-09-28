import groovy.xml.slurpersupport.NodeChildren;
import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;


public class RestAssuredXML {
    @Test
    void countAndListOfsNames() {
        NodeChildrenImpl sNames = RestAssured.given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("ArrayOftContinent.tContinent.sName");
        System.out.println(sNames.size());
        System.out.println(sNames.list());
    }

    @Test
    void lastSNameValue() {
        String sNameWithsCodeAN = RestAssured.given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("**.find { it.sCode == 'AN' }.sName");
        System.out.println(sNameWithsCodeAN);
        assert(sNameWithsCodeAN.equals("Antarctica"));
    }

    @Test
    void extractLastTContinentSName() {
        String lastTContinentSName = RestAssured.given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("ArrayOftContinent.tContinent[-1].sName");
        System.out.println(lastTContinentSName);
        assert(lastTContinentSName.equals("The Americas"));
    }
}
