package utils;

import models.Billing;
import models.PaymentMethod;
import models.User;

import java.util.Random;

public class Utils {
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static User generateRandomUserInfo(){
        int length = 5;
        String name = generateRandomString(false, length);
        String lastName = generateRandomString(false, length);
        String userName = generateRandomString(length);
        String email = generateRandomEmail();
        String password = generateRandomString(length);
        return new User(name, lastName, userName, email, password, password);
    }

    public static PaymentMethod generateRandomPaymentMethod(){
        String cardNumber = generateRandomString(true, 16);
        String dueDate = generateRandomString(true, 2).concat("/").
                concat(generateRandomString(true, 2));
        String code = generateRandomString(true, 3);
        return new PaymentMethod(cardNumber, dueDate, code);
    }

    public static Billing generateRandomBilling(){
        int length = 5;
        String name = generateRandomString(false, length);
        String lastName = generateRandomString(false, length);
        String address = generateRandomString(length);
        String apto = generateRandomString(true, 3);
        String zip = generateRandomString(true, 4);
        String phone = generateRandomString(true, 7);
        String email = generateRandomEmail();
        return new Billing(name, lastName, address, apto, zip, phone, email);
    }

    private static String generateRandomEmail(){
        return generateRandomString(5).concat("@")
                .concat(generateRandomString(false, 3)).concat(".")
                .concat(generateRandomString(false, 3));
    }

    private static String generateRandomString(int length){
        String validInput = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder stringBuilder = new StringBuilder(length);
        int index;

        for (int i = 0; i < length; i++) {
            index = generateRandomInt(0, validInput.length()-1);
            stringBuilder.append(validInput.charAt(index));
        }
        return stringBuilder.toString();
    }

    private static String generateRandomString(boolean isNumber, int length){
        String validInput;
        if(isNumber){
            validInput = "0123456789";
        } else {
            validInput = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        }
        StringBuilder stringBuilder = new StringBuilder(length);
        int index;

        for (int i = 0; i < length; i++) {
            index = generateRandomInt(0, validInput.length()-1);
            stringBuilder.append(validInput.charAt(index));
        }
        return stringBuilder.toString();
    }
}
