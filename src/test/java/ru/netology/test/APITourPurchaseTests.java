package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.APIHelp;
import ru.netology.data.DataGenerator;



public class APITourPurchaseTests {
    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void teardown(){
        SelenideLogger.removeListener("allure");
    }


    @Test
    void paymentPurchaseStatusApproved() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourAPI(); //APIHelp.getInfoBuyingTour();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        System.out.println("запрос  === " + actualRequest) ;
        System.out.println("запрос id == " + actualRequest.getId() + "запрос status == " + actualRequest.getStatus()) ;
        Assertions.assertEquals("APPROVED", actualRequest.getStatus());
    }
    @Test
    void paymentPurchaseStatusDeclined() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourDeclineAPI();//APIHelp.getInfoBuyingTourDecline();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void creditPurchaseStatusApproved() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourDeclineAPI();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus()); // APPROVED или DECLINED?
    }
    @Test
    void creditPurchaseStatusDeclined() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourDeclineAPI();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void paymentPurchaseStatusDeclinedInvalid() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourInvalidAllDataExceptCard();
        var actualRequest = APIHelp.sendRequestPaymentPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }

    @Test
    void creditPurchaseStatusDeclinedInvalid() {
        var apiInfoBuyingTour = DataGenerator.getInfoBuyingTourInvalidAllDataExceptCard();
        var actualRequest = APIHelp.sendRequestCreditPurchaseStatus(apiInfoBuyingTour, 200);
        Assertions.assertEquals("DECLINED", actualRequest.getStatus());
    }


}