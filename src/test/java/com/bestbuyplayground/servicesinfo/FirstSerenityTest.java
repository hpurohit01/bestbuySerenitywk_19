package com.bestbuyplayground.servicesinfo;

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

        @Title("Get all data")
        @Test
        public void test001(){
            SerenityRest.rest().given().log().all()
                    .when()
                    .get(EndPoints.GET_ALL_SERVICES)
                    .then()
                    .log().all()
                    .statusCode(200);

        }

        @Title("Get single data by id")
        @Test
        public void test002(){
            SerenityRest.rest().given().log().all()
                    .pathParam("id", 4)
                    .when()
                    .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                    .then()
                    .log().all()
                    .statusCode(200);
        }
}
