package dto;

public class DashBoardDTO {
    private String date;
    private int noOfDays;

    public DashBoardDTO() {
    }

    public DashBoardDTO(String date, int noOfDays) {
        this.date = date;
        this.noOfDays = noOfDays;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }
}
