package com.bestbuyplayground.productinfo;

import com.bestbuyplayground.constants.EndPoints;
import com.bestbuyplayground.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

    @RunWith(SerenityRunner.class)

    public class FirstSerenityTest extends TestBase {

        static int id;


        @Title("Get all the products information")
        @Test
        public void test001(){
            //RestAssured.basePath = Path.PRODUCTS;
            SerenityRest.rest().given()
                    .when()
                    .get(EndPoints.GET_ALL_PRODUCTS)
                    .then()
                    .log().all()
                    .statusCode(200);

        }

        @Title("Get products by id")
        @Test
        public void test002(){
            SerenityRest.rest().given()
                    .when()
                    .pathParam("id",48530)
                    .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                    .then()
                    .log().all()
                    .statusCode(200);
        }



    }
