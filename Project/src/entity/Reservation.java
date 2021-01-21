package entity;

public class Reservation {
    private int resId;
    private int custId;
    private int userId;
    private String transactionDate;
    private String resStatus;

    public Reservation() {
    }

    public Reservation(int resId, String resStatus) {
        this.resId = resId;
        this.resStatus = resStatus;
    }

    public Reservation(int custId, int userId, String transactionDate, String resStatus) {
        this.custId = custId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.resStatus = resStatus;
    }

    public Reservation(int resId, int custId, int userId, String transactionDate, String resStatus) {
        this.resId = resId;
        this.custId = custId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.resStatus = resStatus;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }
}
