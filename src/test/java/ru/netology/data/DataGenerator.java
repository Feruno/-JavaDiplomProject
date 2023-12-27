package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    @Value
    public static class InfoBuyingTour {
        private String numCard;
        private String month;
        private String year;
        private String nameOwner;
        private String CVC_CVV;
    }

    public static InfoBuyingTour getInfoBuyingTour() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "23", "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidNumCard() {
        return new InfoBuyingTour("4444 4444 0000 000@", "12", "23", "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourIncompleteNumCard() {
        return new InfoBuyingTour("4444 4444", "12", "23", "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidMonth() {
        return new InfoBuyingTour("4444 4444 4444 4441", "13", "23", "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidYear() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "22", "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidNameOwner() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "23", "Василий@#", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidCVC_CVV() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "23", "Василий", "10@");
    }

    public static InfoBuyingTour getInfoBuyingTourLengthLimitationNameOwner() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "23", "Василиййййййййййййййййййййййййййййййййййййййй", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourSQLinjections() {
        return new InfoBuyingTour("4444 4444 4444 4441", "12", "23", "SELECT * FROM users WHERE username=' ProvidedUsername' and password='ProvidedPassword';", "101");
    }



}
