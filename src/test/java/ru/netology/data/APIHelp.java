package ru.netology.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.netology.data.DataGenerator.*;

public class APIHelp {
    private APIHelp(){
    }

    @Value
    public static class IdStatusPurchase{
        String id;
        String status;

        @JsonCreator
        public IdStatusPurchase(@JsonProperty("id") String id, @JsonProperty("status") String status){
            this.id = id;
            this.status = status;
        }
    }

    @Value
    public static class APIInfoBuyingTour {
        String number; // numCard number
        String month; // month
        String year; // year
        String holder; // nameOwner holder
        String cvc; // CVC_CVV cvc
    }
    public static APIInfoBuyingTour getInfoBuyingTour() {
        return new APIInfoBuyingTour("4444 4444 4444 4441", dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static APIInfoBuyingTour getInfoBuyingTourDecline() {
        return new APIInfoBuyingTour("4444 4444 4444 4442", dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static APIInfoBuyingTour getInfoBuyingTourInvalidAllDataExceptCard() {
        return new APIInfoBuyingTour("4444 4444 4444 4441", "13", "20", "Select * From", "10@");
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

//    public static Map<String, String> sendRequestPaymentPurchaseAnswer(APIInfoBuyingTour apiInfoBuyingTour, Integer statusCodeWait) {
//        IdStatusPurchase[] buy = given()
//                .spec(requestSpec)
//                .body(apiInfoBuyingTour)
//                .when()
//                .post("/payment")
//                .then().log().all()
//                .statusCode(statusCodeWait)
//                .extract()
//                .body()
//                .as(IdStatusPurchase[].class);
//        Map<String, String> purchas = new HashMap<>();
//        for (IdStatusPurchase purchaseItem: buy){
//            purchas.put(purchaseItem.getId(), purchaseItem.getStatus());
//        }
//        return purchas;
//    }
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