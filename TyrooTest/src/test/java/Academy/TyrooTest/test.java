package Academy.TyrooTest;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class test {

	//public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
    @Test	
    public void TyroGameofThronesvalidation() throws InterruptedException
    {
	
      RestAssured.baseURI = "https://imdb8.p.rapidapi.com/title/find";
	  String response = 	given().log().all().queryParam("q", "game of thrones")
			  .header("x-rapidapi-key", "91a8fae4d3mshf4b5423bb44bef9p1f6f12jsn3e84964a11be")
	 .header("x-rapidapi-host","imdb8.p.rapidapi.com").header("useQueryString","true")		
	 .when().get().then().assertThat().statusCode(200).extract().response().asString();
	
	    JsonPath js = new JsonPath(response);
   
	   //Getting the title, title type, year of the 0th row.
		String firstTitle = js.get("results[0].title");
		System.out.println(firstTitle);
		String firstTitleType = js.get("results[0].titleType");
		System.out.println(firstTitleType);
		int firstYear = js.getInt("results[0].year");
		String frstYear = Integer.toString(firstYear); //converting int to string
		System.out.println(frstYear);

		//Getting the title, title type, year of the 1st row.
		String SecondTitle = js.get("results[1].title");
		System.out.println(SecondTitle);
		String SecondTitleType = js.get("results[1].titleType");
		System.out.println(SecondTitleType);
		int SecondYear = js.getInt("results[1].year");
		String scndYear = Integer.toString(SecondYear);
		System.out.println(scndYear); //converting int to string
  
		//Getting the title, title type, year of the 2nd row.
		String thirdTitle = js.get("results[2].title");
		System.out.println(thirdTitle);
		String thirdTitleType = js.get("results[2].titleType");
		System.out.println(thirdTitleType);
		int thirdYear = js.getInt("results[2].year");
		String thrdYear = Integer.toString(thirdYear);
		System.out.println(thrdYear);  //converting int to string
		
        //Setting the chrome driver property
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe"); //place the location here here your chrome driver is placed
		WebDriver driver = new ChromeDriver();
		
		//defining Implicit wait for 5 seconds.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.imdb.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='suggestion-search']")).sendKeys("game of thrones");
		driver.findElement(By.xpath("//body/div[@id='IMDbHomepageSiteReactViews']/nav[@id='imdbHeader']/div[2]/div[1]/form[1]/button[1]/*[1]")).click();

		//Getting the 1st data set, title, title type, year from UI.
		String firstResultSet = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().trim();
		String[] resOne = firstResultSet.split("\\(");
		String titleOne = resOne[0].trim();
		String titleTypeOne = resOne[1].replaceAll("\\)", " ").trim();
		String yearOne = resOne[2].replaceAll("\\)", " ").trim();
		System.out.println(titleOne);
		System.out.println(titleTypeOne);
		System.out.println(yearOne);

		//Validating if the title, title type, title year of the first entry is equal.
		boolean ifFirstResSetEqual = true;
		if (!firstTitle.equals(titleOne) && firstTitleType.equals(titleTypeOne) && frstYear.equals(yearOne)) {
			ifFirstResSetEqual = false;
		}
		Assert.assertTrue(ifFirstResSetEqual);
		

		//Getting the 2nd data set, title, title type, year from UI.
		String secondResultSet = driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText().trim();
		String[] resTwo = secondResultSet.split("\\(");
		String titleTwo = resTwo[0].trim();
		String TitletypeTwo = resTwo[1].replaceAll("\\)", " ").trim();
		String yearTwo = resTwo[2].replaceAll("\\)", " ").trim();
		System.out.println(titleTwo);
		System.out.println(TitletypeTwo);
		System.out.println(yearTwo);

		//Validating if the title, title type, title year of the second entry is equal.
		boolean ifSecondResSetEqual = true;
		if (!SecondTitle.equals(titleTwo) && SecondTitleType.equals(TitletypeTwo) && scndYear.equals(yearTwo)) {
			ifSecondResSetEqual = false;
		}
		Assert.assertTrue(ifSecondResSetEqual);
		

		//Getting the 3rd data set, title, title type, year from UI.
		String thirdResultSet = driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText().trim();
		String[] resThree = thirdResultSet.split("\\(");
		String titlethree = resThree[0].trim();
		String titleTypeThree = resThree[1].replaceAll("\\)", " ").trim();
		String yearThree = resThree[2].replaceAll("\\)", " ").trim();
		System.out.println(titlethree);
		System.out.println(titleTypeThree);
		System.out.println(yearThree);

		//Validating if the title, title type, title year of the second entry is equal.
		boolean ifThirdResSetEqual = true;

		if (!thirdTitle.equals(titlethree) && thirdTitleType.equals(titleTypeThree) && thrdYear.equals(yearThree)) {
			ifThirdResSetEqual = false;
		}

		Assert.assertTrue(ifThirdResSetEqual);
		
		//closing the window
		driver.close();
		 
}
	
}

