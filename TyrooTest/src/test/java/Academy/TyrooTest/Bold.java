package Academy.TyrooTest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Bold {
		
	@Test
	public void ValidatePostrequest()
	{
		
		//This code is not working since there is no proper API contract, I just used the dummy format given in the test, but this
		//will be the approach I will move with when having real data
		
		//post request
		RestAssured.baseURI = "test.bold.com";
		  String postResponse = given().log().all().body("{planName:”<SomeName>”, city : “<city>”, description : “<description>” } ")
		 .when().post("api/v1/create").then().log().all().assertThat().statusCode(200).extract().response().asString();
		  
		  //reading the data obtained in body from post request
		  JsonPath js = new JsonPath(postResponse);
		  String planName = js.get("planName");
		  System.out.println(planName);
		  String city = js.get("city");
		  System.out.println(city);
		  String description = js.get("description");
		  System.out.println(description);
		  
		  //get request
		  String getResponse = 	given().log().all()		
		 .when().get("api/v1/{planName}").then().assertThat().statusCode(200).extract().response().asString();
		  
		  
		  //reading the data obtained in body from get request
		  JsonPath js1 = new JsonPath(getResponse);
		  String actualPlanName = js1.get("somename");
		  System.out.println(actualPlanName);
		  String actualCity = js.get("city");
		  System.out.println(actualCity);
		  String actualDescription = js.get("description");
		  System.out.println(actualDescription);
		  
		  //validating if the data obtained by post and get are matching or not.
		  Assert.assertEquals(planName, actualPlanName);
		  Assert.assertEquals(city, actualCity);
		  Assert.assertEquals(description, actualDescription);
		
		

	}

}
