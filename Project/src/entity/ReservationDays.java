package entity;

public class ReservationDays {
    private int resDayId;
    private int resDetailId;
    private String resDate;

    public ReservationDays() {
    }

    public ReservationDays(int resDetailId, String resDate) {
        this.resDetailId = resDetailId;
        this.resDate = resDate;
    }

    public ReservationDays(int resDayId, int resDetailId, String resDate) {
        this.resDayId = resDayId;
        this.resDetailId = resDetailId;
        this.resDate = resDate;
    }

    public int getResDayId() {
        return resDayId;
    }

    public void setResDayId(int resDayId) {
        this.resDayId = resDayId;
    }

    public int getResDetailId() {
        return resDetailId;
    }

    public void setResDetailId(int resDetailId) {
        this.resDetailId = resDetailId;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }
}
