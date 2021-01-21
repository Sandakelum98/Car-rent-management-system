package dto;

public class ReservationDTO {
    private int resId;
    private int custId;
    private int userId;
    private String transactionDate;
    private String resStatus;

    public ReservationDTO() {
    }

    public ReservationDTO(int resId, String resStatus) {
        this.resId = resId;
        this.resStatus = resStatus;
    }

    public ReservationDTO(int custId, int userId, String transactionDate, String resStatus) {
        this.setCustId(custId);
        this.setUserId(userId);
        this.setTransactionDate(transactionDate);
        this.setResStatus(resStatus);
    }

    public ReservationDTO(int resId, int custId, int userId, String transactionDate, String resStatus) {
        this.setResId(resId);
        this.setCustId(custId);
        this.setUserId(userId);
        this.setTransactionDate(transactionDate);
        this.setResStatus(resStatus);
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
