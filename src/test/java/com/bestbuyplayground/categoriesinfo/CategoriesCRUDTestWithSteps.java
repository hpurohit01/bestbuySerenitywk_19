package com.bestbuyplayground.categoriesinfo;

import com.bestbuyplayground.bestbuyinfo.CategoriesSteps;
import com.bestbuyplayground.constants.EndPoints;
import com.bestbuyplayground.model.CategoriesPojo;
import com.bestbuyplayground.testbase.TestBase;
import com.bestbuyplayground.utilis.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)

    public class CategoriesCRUDTestWithSteps extends TestBase {

        static String name = "ItProfessionals" + TestUtils.getRandomValue();
        static String categoryId;

        @Steps
        CategoriesSteps categoriesSteps;

        @Title("creating and verify the category data to an application")
        @Test
        public void test001(){

            HashMap<String,Object> pvalue = categoriesSteps.createCategory(name)
                    .log().all().statusCode(201).extract().response().body().jsonPath().get();

            System.out.println(pvalue);
            assertThat(pvalue,hasValue(name));
            categoryId = (String) pvalue.get("id");
            System.out.println(categoryId);
        }

        @Title("Update and verify the data in category application")
        @Test
        public void test002(){

            name = name + "_Updated";

            CategoriesPojo categoriesPojo = new CategoriesPojo();
            categoriesPojo.setName(name);

            SerenityRest.rest().given().log().all()
                    .header("Content-Type", "application/json")
                    .pathParam("categoryId",categoryId)
                    .body(categoriesPojo)
                    .when()
                    .put(EndPoints.UPDATE_CATEGORIES_BY_ID)
                    .then()
                    .log().all().statusCode(200);

            HashMap<String,Object> pvalue =
                    SerenityRest.rest().given().log().all()
                            .when()
                            .get(EndPoints.GET_ALL_CATEGORIES)
                            .then()
                            .log().all().statusCode(200)
                            .extract().response().body().jsonPath().get();
            assertThat(pvalue,hasValue(name));
        }

        @Title("Deleting and verify the product is deleted")
        @Test
        public void test003(){
            SerenityRest.rest().given().log().all()
                    .pathParam("categoryId",categoryId)
                    .when()
                    .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                    .then()
                    .statusCode(200);

            SerenityRest.rest().given().log().all()
                    .pathParam("categoryId",categoryId)
                    .when()
                    .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                    .then()
                    .statusCode(404);
        }
    }

