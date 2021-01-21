package dto;

public class IssueDTO {
    private String custName;
    private String custNIC;
    private String custAddress;
    private String mobileNo;
    private String vehicleMake;
    private String getVehicleModel;
    private String vehicleNo;
    private int vehicleYear;
    private String vehicleColour;
    private int resId;
    private int resDetailId;
    private double defaultRate;
    private int km;
    private double addingRate;
    private String fromDate;
    private String toDate;
    private int startMileage;
    private String reStatus;

    public IssueDTO() {
    }

    public IssueDTO(int resId, String reStatus, int resDetailId, int startMileage) {
        this.resId = resId;
        this.reStatus = reStatus;
        this.resDetailId = resDetailId;
        this.startMileage = startMileage;
    }

    public IssueDTO(String custName, String custNIC, String custAddress, String mobileNo, String vehicleMake, String getVehicleModel, String vehicleNo, int vehicleYear, String vehicleColour, int resId, int resDetailId, double defaultRate, int km, double addingRate, String fromDate, String toDate) {
        this.custName = custName;
        this.custNIC = custNIC;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
        this.vehicleMake = vehicleMake;
        this.getVehicleModel = getVehicleModel;
        this.vehicleNo = vehicleNo;
        this.vehicleYear = vehicleYear;
        this.vehicleColour = vehicleColour;
        this.resId = resId;
        this.resDetailId = resDetailId;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustNIC() {
        return custNIC;
    }

    public void setCustNIC(String custNIC) {
        this.custNIC = custNIC;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getGetVehicleModel() {
        return getVehicleModel;
    }

    public void setGetVehicleModel(String getVehicleModel) {
        this.getVehicleModel = getVehicleModel;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getResDetailId() {
        return resDetailId;
    }

    public void setResDetailId(int resDetailId) {
        this.resDetailId = resDetailId;
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

    public String getReStatus() {
        return reStatus;
    }

    public void setReStatus(String reStatus) {
        this.reStatus = reStatus;
    }

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }
}
