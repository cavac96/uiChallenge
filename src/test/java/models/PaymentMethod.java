package models;

public class PaymentMethod {
    String cardNumber;
    String dueDate;
    String code;

    public PaymentMethod(String cardNumber, String dueDate, String code) {
        this.cardNumber = cardNumber;
        this.dueDate = dueDate;
        this.code = code;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCode() {
        return code;
    }
}
