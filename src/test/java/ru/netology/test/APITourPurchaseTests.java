package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.APIHelp;
import ru.netology.data.DataGenerator;
import ru.netology.data.HelpSQL;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.restassured.RestAssured.given;


public class APITourPurchaseTests {

//    @Test
//    void successfulPathAnswer() {
//        var apiInfoBuyingTour = APIHelp.getInfoBuyingTour();
//
//        String request = String.valueOf(APIHelp.sendRequestPaymentPurchaseAnswer(apiInfoBuyingTour, 200));
//
//        System.out.println("запрос == " + request);
//    }

    @Test
    void paymentPurchaseStatusApproved() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTour();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        System.out.println("запрос  === " + actualRequest) ;
        System.out.println("запрос id == " + actualRequest.getId() + "запрос status == " + actualRequest.getStatus()) ;
        Assertions.assertEquals("APPROVED", actualRequest.getStatus());
    }
    @Test
    void paymentPurchaseStatusDeclined() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTourDecline();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void creditPurchaseStatusApproved() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTourDecline();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus()); // APPROVED или DECLINED?
    }
    @Test
    void creditPurchaseStatusDeclined() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTourDecline();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void paymentPurchaseStatusDeclinedInvalid() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTourInvalidAllDataExceptCard();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void creditPurchaseStatusDeclinedInvalid() {
        var apiInfoBuyingTour = APIHelp.getInfoBuyingTourInvalidAllDataExceptCard();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }


}