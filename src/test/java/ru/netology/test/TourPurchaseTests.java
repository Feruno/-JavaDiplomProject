package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.data.HelpSQL;
import ru.netology.page.TourPurchasePage;



import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.HelpSQL.cleanDatabase;


public class TourPurchaseTests {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void teardown(){
        cleanDatabase();
        SelenideLogger.removeListener("allure");
    }

    /*
     *  //span[text()[contains(.,'Номер карты')]]
     *  //span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]
     * */


    @Test
    void successfulPath() {
        var infoBuyTour = DataGenerator.getInfoBuyingTour();
        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.successfulPurchase();

        String res = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", res);
    }


    //xpath //span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]
    @org.junit.jupiter.api.Test
    void invalidNumCard() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNumCard();
        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase();
    }

    @org.junit.jupiter.api.Test
    void incompleteNumCard() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourIncompleteNumCard();
        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase();
    }

    @org.junit.jupiter.api.Test
    void invalidMonth() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidMonth();
        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidMonth();
    }

    @org.junit.jupiter.api.Test
    void invalidYear() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidYear();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidYear();

    }

    @org.junit.jupiter.api.Test
    void invalidNameOwner() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNameOwner();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidNameOwner();

        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void invalidCVC_CVV() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidCVC_CVV();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.InvalidCVC();
    }

    @org.junit.jupiter.api.Test
    void emptyAllFields() {

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase(); //мб переименовать
        tourPurchaseElemPage.emptyInvalidMonth();
        tourPurchaseElemPage.emptyInvalidYear();
        tourPurchaseElemPage.emptyInvalidNameOwner();
        tourPurchaseElemPage.InvalidCVC();
    }

    @org.junit.jupiter.api.Test
    void lengthLimitation() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourLengthLimitationNameOwner();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.successfulPurchase();
        tourPurchaseElemPage.invalidNameOwner();

        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void injectionsSQL() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourSQLinjections();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.successfulPurchase();
        tourPurchaseElemPage.invalidNameOwner();

        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", actualRes);
    }

    /*
     * Тесты по оплате тура в кредит - "Кредит по данным карты"
     *
     *
     *
     * */
    @org.junit.jupiter.api.Test
    void successfulPathCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTour();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.successfulPurchase();

        String res = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", res);
    }


    @org.junit.jupiter.api.Test
    void invalidNumCardCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNumCard();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase();
    }

    @org.junit.jupiter.api.Test
    void incompleteNumCardCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourIncompleteNumCard();
        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase();

    }

    @org.junit.jupiter.api.Test
    void invalidMonthCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidMonth();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidMonth();
    }

    @org.junit.jupiter.api.Test
    void invalidYearCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidYear();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidYear();

    }

    @org.junit.jupiter.api.Test
    void invalidNameOwnerCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNameOwner();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidNameOwner();
    }

    @org.junit.jupiter.api.Test
    void invalidCVC_CVVCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidCVC_CVV();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.InvalidCVC();
    }

    @org.junit.jupiter.api.Test
    void emptyAllFieldsCreditCardData() {

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidPurchase(); //мб переименовать
        tourPurchaseElemPage.emptyInvalidMonth();
        tourPurchaseElemPage.emptyInvalidYear();
        tourPurchaseElemPage.emptyInvalidNameOwner();
        tourPurchaseElemPage.InvalidCVC();
    }

    @org.junit.jupiter.api.Test
    void lengthLimitationCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourLengthLimitationNameOwner();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.invalidNameOwner();

        String actualRes = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void injectionsSQLCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourSQLinjections();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();

        String b = String.valueOf(HelpSQL.getInfoCreditPurStatus());
        System.out.println("статус " + b);

        tourPurchaseElemPage.invalidNameOwner();

        String actualRes = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", actualRes);
    }

    /*
    тесты с decline статусом
    */

    @org.junit.jupiter.api.Test
    void declinePurchase() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourDecline();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.failedPurchase();

        String res = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", res);
    }

    @org.junit.jupiter.api.Test
    void declineCreditPurchase() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourDecline();

        var tourPurchaseElemPage = new TourPurchasePage();
        tourPurchaseElemPage.buyOnCreditButton();
        tourPurchaseElemPage.infoTourPurchase(infoBuyTour);
        tourPurchaseElemPage.nextButton();
        tourPurchaseElemPage.failedPurchase();

        String res = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", res);
    }
}