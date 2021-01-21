package dto;

public class ReservationDetailDTO {
    private int resDetailId;
    private int resId;
    private int vehicleId;
    private String fromDate;
    private String toDate;
    private double defaultRate;
    private int km;
    private double addingRate;
    private int startMileage;
    private int endMileage;
    private double totalAmount;

    public ReservationDetailDTO() {
    }

    public ReservationDetailDTO(int resDetailId, int endMileage) {
        this.resDetailId = resDetailId;
        this.endMileage = endMileage;
    }

    public ReservationDetailDTO(int resDetailId, double totalAmount) {
        this.resDetailId = resDetailId;
        this.totalAmount = totalAmount;
    }

    public ReservationDetailDTO(int resId, int vehicleId, String fromDate, String toDate, double defaultRate, int km, double addingRate) {
        this.resId = resId;
        this.vehicleId = vehicleId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
    }

    public ReservationDetailDTO(int resDetailId, int resId, int vehicleId, String fromDate, String toDate, double defaultRate, int km, double addingRate, int startMileage, int endMileage) {
        this.resDetailId = resDetailId;
        this.resId = resId;
        this.vehicleId = vehicleId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
        this.startMileage = startMileage;
        this.endMileage = endMileage;
    }

    public int getResDetailId() {
        return resDetailId;
    }

    public void setResDetailId(int resDetailId) {
        this.resDetailId = resDetailId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public double getAddingRate() {
        return addingRate;
    }

    public void setAddingRate(double addingRate) {
        this.addingRate = addingRate;
    }

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }

    public int getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(int endMileage) {
        this.endMileage = endMileage;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
