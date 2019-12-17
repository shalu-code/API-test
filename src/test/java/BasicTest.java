import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BasicTest {
    public static String authToken;

    @Test
    public void testStatusCode() {

        given()
                .get("http://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products")
                .then()
                .statusCode(200);

    }

    @Test
    public void testLogging() {

        given()
                .log().all()
                .get("http://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
    }

    @Test
    public void printResponse() {
        Response res = given().when()
                .log().all()
                .get("http://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");

        System.out.println(res.prettyPrint());
        //System.out.println(*****************);

    }

    @Test
    public void testCurrency() {

        Response res = given()
                .get("http://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        JsonPath jsonpath = res.jsonPath();
        List<Map> products = jsonpath.getList("data");
        for (Map product : products) {

            Map attributes = (Map) product.get("attributes");

            Assert.assertTrue(attributes.get(("currency")).equals("USD"));
            //Assert.assertTrue(attributes.containsValue("USD"));


        }

        //.then()

        // .body("data[0].attributes.currency", equalTo("USD"));


    }

    @Test
    public void testFilterName() {
      Response res=  given()
                .log().all()
                .queryParam("filter[name]","Cloth")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
                 System.out.println(res.prettyPrint());
    }
    @Test
    public void testFilterId(){
        Response res=  given()
                .log().all()
                .queryParam("filter[ids]","3")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPeek());


    }
   /* @Test
    public void testFilterSkus(){
        Response res=  given()
                .log().all()
                .queryParam("filter[skus]","3")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPeek());


    }*/

    @Test
    public void testFilterPrice(){
        Response res=  given()
                .log().all()
                .queryParam("filter[price]","19.99")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPeek());


    }
    @Test
    public void testFilterTaxons(){
        Response res=  given()
                .log().all()
                .queryParam("filter[taxons]","7")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPeek());


    }
    /*@Test
    public void testFilterOptionColor( ){
        Response res=  given()
                .log().all()
                .queryParam("filter[options][tshirt-color]","7")
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/products");
        System.out.println(res.prettyPeek());


    }*/
    @Test
    public void testPostCall() {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authToken);
        String createBody = "{\n" +
                "  \"variant_id\": \"17\",\n" +
                "  \"quantity\": 5\n" +
                "}";
        Response res = given()
                .headers(headers)
                .body(createBody)
                .when()
                .post("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/add_item");
        Assert.assertEquals(res.statusCode(), 200);
    }




    @BeforeClass
    public void authToken() {

        Response res=given()
                .formParam("grant_type","password")
                .formParam("username","shalinip1017@gmail.com")
                .formParam("password","shalu1234")
                .post("https://spree-vapasi-prod.herokuapp.com/spree_oauth/token");
        System.out.println(res.prettyPrint());
        authToken="Bearer " + res.path("access_token");
        System.out.println(authToken);

    }
    @Test
    public void delItem(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authToken);
        Response res = given()
                .headers(headers)
                .when()
                .delete("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart/remove_line_item/422");

        Assert.assertEquals(res.statusCode(), 200);



    }
    @Test
    public void viewCart(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authToken);
        Response res = given()
                .headers(headers)
                .when()
                .get("https://spree-vapasi-prod.herokuapp.com/api/v2/storefront/cart");


        Assert.assertEquals(res.statusCode(), 200);
        System.out.println(res.prettyPeek());



    }
}
