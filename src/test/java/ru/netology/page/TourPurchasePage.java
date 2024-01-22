package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TourPurchasePage {

    private SelenideElement buyButton = $(byText("Купить"));

    private SelenideElement buyOnCreditButton = $(byText("Купить в кредит"));

    private SelenideElement nextButton = $(byText("Продолжить"));

    public TourPurchasePage() {
        buyButton.shouldBe(Condition.visible);//под вопросом
    }

    public void buyButton() {
        buyButton.click();
    }
    public void buyOnCreditButton() {
        buyOnCreditButton.click();
    }
    public void nextButton() {
        nextButton.click();
    }

    public void infoTourPurchase(DataGenerator.InfoBuyingTour info) {
        $x("//span[text()[contains(.,'Номер карты')]]/..//input[contains(@class, 'input__control')]").setValue(info.getNumCard());
        $x("//span[text()[contains(.,'Месяц')]]/..//input[contains(@class, 'input__control')]").setValue(info.getMonth());
        $x("//span[text()[contains(.,'Год')]]/..//input[contains(@class, 'input__control')]").setValue(info.getYear());
        $x("//span[text()[contains(.,'Владелец')]]/..//input[contains(@class, 'input__control')]").setValue(info.getNameOwner());
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//input[contains(@class, 'input__control')]").setValue(info.getCVC_CVV());
    }

    public void successfulPurchase() {
        $(".notification__content")
                .shouldHave(Condition.text("Операция одобрена Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    public void failedPurchase() {
        $(".notification__content")
                .shouldHave(Condition.text("Операция отклонена  Банком."), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    public void invalidPurchase() {
        $x("//span[text()[contains(.,'Номер карты')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"))
                .shouldBe(Condition.visible);
    }

    public void invalidMonth(){
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверно указан срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверно указан срок действия карты"))
                .shouldBe(Condition.visible);
    }
    public void emptyInvalidMonth(){
        $x("//span[text()[contains(.,'Месяц')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"))
                .shouldBe(Condition.visible);
    }
    public void invalidYear(){
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Истёк срок действия карты')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Истёк срок действия карты"))
                .shouldBe(Condition.visible);
    }
    public void emptyInvalidYear(){
        $x("//span[text()[contains(.,'Год')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"))
                .shouldBe(Condition.visible);
    }
    public void invalidNameOwner(){
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"))
                .shouldBe(Condition.visible);
    }
    public void emptyInvalidNameOwner(){
        $x("//span[text()[contains(.,'Владелец')]]/..//span[text()[contains(.,'Поле обязательно для заполнения')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }
    public void InvalidCVC(){
        $x("//span[text()[contains(.,'CVC/CVV')]]/..//span[text()[contains(.,'Неверный формат')]]/..//span[contains(@class, 'input__sub')]")
                .shouldHave(Condition.text("Неверный формат"))
                .shouldBe(Condition.visible);
    }
}