package entity;

public class Payment {
    private int paymentId;
    private int resID;
    private String paymentDate;
    private String paymentType;
    private double amount;

    public Payment() {
    }

    public Payment(int resID, String paymentDate, String paymentType, double amount) {
        this.resID = resID;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Payment(int paymentId, int resID, String paymentDate, String paymentType, double amount) {
        this.paymentId = paymentId;
        this.resID = resID;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
