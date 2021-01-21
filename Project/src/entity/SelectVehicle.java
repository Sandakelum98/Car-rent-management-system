package entity;

public class SelectVehicle {
    private int vehicleId;
    private String vehicleNo;
    private int vehicleYear;
    private String vehicleColour;
    private String makeName;
    private String modelName;
    private double defaultRate;
    private int km;
    private double addingRate;
    private String fromDate;
    private String toDate;
    private String selectedModel;

    public SelectVehicle() {
    }

    public SelectVehicle(String fromDate, String toDate, String selectedModel) {
        this.setFromDate(fromDate);
        this.setToDate(toDate);
        this.setSelectedModel(selectedModel);
    }

    public SelectVehicle(int vehicleId, String vehicleNo, int vehicleYear, String vehicleColour, String makeName, String modelName, double defaultRate, int km, double addingRate, String fromDate, String toDate) {
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.vehicleYear = vehicleYear;
        this.vehicleColour = vehicleColour;
        this.makeName = makeName;
        this.modelName = modelName;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public String getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(String selectedModel) {
        this.selectedModel = selectedModel;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
}
