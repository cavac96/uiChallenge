package utils;

import models.Billing;
import models.PaymentMethod;
import models.User;

import java.io.*;

public class FileHandler {
    public static User readTextFileUser() {
        File file = new File("src/registerFile.txt");
        BufferedReader br = null;
        User user = null;
        try {
            br = new BufferedReader(new FileReader(file));
            user = new User(br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User readTextFileLogin() {
        File file = new File("src/loginFile.txt");
        BufferedReader br = null;
        User user = null;
        try {
            br = new BufferedReader(new FileReader(file));
            user = new User(br.readLine(), br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static PaymentMethod readTextFilePaymentMethod() {
        File file = new File("src/paymentMethodFile.txt");
        BufferedReader br = null;
        PaymentMethod paymentMethod = null;
        try {
            br = new BufferedReader(new FileReader(file));
            paymentMethod = new PaymentMethod(br.readLine(), br.readLine(), br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentMethod;
    }

    public static Billing readTextFileBilling() {
        File file = new File("src/billingFile.txt");
        BufferedReader br = null;
        Billing billing = null;
        try {
            br = new BufferedReader(new FileReader(file));
            billing = new Billing(br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine(), br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billing;
    }
}
