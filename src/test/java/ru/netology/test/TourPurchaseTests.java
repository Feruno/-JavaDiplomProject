package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataGenerator;
import ru.netology.data.HelpSQL;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.time.Year;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selectors.byText;
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


    @org.junit.jupiter.api.Test
    void successfulPath() {
        var infoBuyTour = DataGenerator.getInfoBuyingTour();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);


        String res = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", res);
    }


    //xpath //span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]
    @org.junit.jupiter.api.Test
    void invalidNumCard() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNumCard();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @org.junit.jupiter.api.Test
    void incompleteNumCard() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourIncompleteNumCard();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidMonth() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidMonth();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверно указан срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidYear() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidYear();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Истёк срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidNameOwner() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNameOwner();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void invalidCVC_CVV() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidCVC_CVV();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void emptyAllFields() {
        $(byText("Купить")).click();
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Поле обязательно для заполнения')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void lengthLimitation() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourLengthLimitationNameOwner();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void injectionsSQL() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourSQLinjections();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String actualRes = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", actualRes);
    }

    /*
     * Тесты по оплате тура - "Кредит по данным карты"
     *
     *
     *
     * */
    @org.junit.jupiter.api.Test
    void successfulPathCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTour();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String res = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("APPROVED", res);
    }


    @org.junit.jupiter.api.Test
    void invalidNumCardCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNumCard();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void incompleteNumCardCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourIncompleteNumCard();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidMonthCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidMonth();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверно указан срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidYearCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidYear();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Истёк срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidNameOwnerCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidNameOwner();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void invalidCVC_CVVCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourInvalidCVC_CVV();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void emptyAllFieldsCreditCardData() {
        $(byText("Купить в кредит")).click();
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Поле обязательно для заполнения')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Поле обязательно для заполнения"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @org.junit.jupiter.api.Test
    void lengthLimitationCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourLengthLimitationNameOwner();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String actualRes = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", actualRes);
    }

    @org.junit.jupiter.api.Test
    void injectionsSQLCreditCardData() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourSQLinjections();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        String b = String.valueOf(HelpSQL.getInfoCreditPurStatus());
        System.out.println("статус " + b);
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String actualRes = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", actualRes);
    }

    /*
    тесты с decline статусом
    */

    @org.junit.jupiter.api.Test
    void declinePurchase() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourDecline();
        SelenideElement form = $("form");
        $(byText("Купить")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String res = HelpSQL.getInfoPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", res);
    }

    @org.junit.jupiter.api.Test
    void declineCreditPurchase() {
        var infoBuyTour = DataGenerator.getInfoBuyingTourDecline();
        SelenideElement form = $("form");
        $(byText("Купить в кредит")).click();
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(infoBuyTour.getCVC_CVV());
        $(byText("Продолжить")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String res = HelpSQL.getInfoCreditPurStatus().getStatus();
        Assertions.assertEquals("DECLINED", res);
    }
}