package dto;

public class PaymentDetailDTO {
    private int paymentDetailId;
    private int paymentId;
    private double amount;
    private String paymentType;
    private int cardNumber;

    public PaymentDetailDTO() {
    }

    public PaymentDetailDTO(double amount, String paymentType, int cardNumber) {
        this.amount = amount;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
    }

    public PaymentDetailDTO(int paymentId, double amount, String paymentType, int cardNumber) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
    }

    public PaymentDetailDTO(int paymentDetailId, int paymentId, double amount, String paymentType, int cardNumber) {
        this.paymentDetailId = paymentDetailId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
    }

    public int getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(int paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
