package ru.netology.data;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonCreator;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;


import static io.restassured.RestAssured.given;
import static ru.netology.data.DataGenerator.*;

public class APIHelp {
    private APIHelp(){
    }


    @Value
    public static class IdStatusPurchase{
        String id;
        String status;
    }

    @Value
    @AllArgsConstructor
    @Data
    public static class APIInfoBuyingTour {
        String number; // numCard number
        String month; // month
        String year; // year
        String holder; // nameOwner holder
        String cvc; // CVC_CVV cvc
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static IdStatusPurchase sendRequestPaymentPurchaseStatus(APIInfoBuyingTour apiInfoBuyingTour, Integer statusCodeWait) {
        return given()
                .spec(requestSpec)
                .body(apiInfoBuyingTour)
                .when()
                .post("/payment")
                .then()
                .statusCode(statusCodeWait)
                .extract()
                .body()
                .as(IdStatusPurchase.class);
    }


    public static IdStatusPurchase sendRequestCreditPurchaseStatus(APIInfoBuyingTour apiInfoBuyingTour, Integer statusCodeWait) {
        return given()
                .spec(requestSpec)
                .body(apiInfoBuyingTour)
                .when()
                .post("/credit")
                .then()
                .statusCode(statusCodeWait)
                .extract()
                .body()
                .as(IdStatusPurchase.class);
    }
}