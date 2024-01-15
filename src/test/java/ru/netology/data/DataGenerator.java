package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    @Value
    public static class InfoBuyingTour {
        private String numCard; // numCard number
        private String month; // month
        private String year; // year
        private String nameOwner; // nameOwner holder
        private String CVC_CVV; // CVC_CVV cvc
    }

    static Date date = faker.date().future(365, TimeUnit.DAYS);
    static Date datePast = faker.date().past(1095, TimeUnit.DAYS);
    static SimpleDateFormat dateSimpleYear = new SimpleDateFormat("yy");
    static SimpleDateFormat dateSimpleMonth = new SimpleDateFormat("MM");

    static String numCardApproved = "4444 4444 4444 4441";
    static String numCardDeclined = "4444 4444 4444 4442";



    public static InfoBuyingTour getInfoBuyingTour() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidNumCard() {
        return new InfoBuyingTour("4444 4444 0000 000@", dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourIncompleteNumCard() {
        return new InfoBuyingTour("4444 4444", dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidMonth() {
        return new InfoBuyingTour(numCardApproved, "13", dateSimpleYear.format(date), "Василий", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidYear() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(datePast), "Василий", "101"); //22 год был прописан вручную
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidNameOwner() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий@#", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourInvalidCVC_CVV() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "10@");
    }

    public static InfoBuyingTour getInfoBuyingTourLengthLimitationNameOwner() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василиййййййййййййййййййййййййййййййййййййййй", "101");
    }
    public static InfoBuyingTour getInfoBuyingTourSQLinjections() {
        return new InfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "SELECT * FROM users WHERE username=' ProvidedUsername' and password='ProvidedPassword';", "101");
    }

    public static InfoBuyingTour getInfoBuyingTourDecline() {
        return new InfoBuyingTour(numCardDeclined, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }


    /*для helpSQL*/
    public static APIHelp.APIInfoBuyingTour getInfoBuyingTourAPI() {
        return new APIHelp.APIInfoBuyingTour(numCardApproved, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static APIHelp.APIInfoBuyingTour getInfoBuyingTourDeclineAPI() {
        return new APIHelp.APIInfoBuyingTour(numCardDeclined, dateSimpleMonth.format(date), dateSimpleYear.format(date), "Василий", "101");
    }
    public static APIHelp.APIInfoBuyingTour getInfoBuyingTourInvalidAllDataExceptCard() {
        return new APIHelp.APIInfoBuyingTour(numCardApproved, "13", "20", "Select * From", "10@");
    }
}
