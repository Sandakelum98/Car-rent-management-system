package dto;

public class VehicleDetailsDTO {
    private int makeId;
    private String makeName;
    private int modelId;
    private String modelName;
    private int vehicleId;
    private String vehicleNo;
    private int vehicleYear;
    private String colour;
    private int isActive;
    private String isActiveName;
    private int priceCateId;
    private String priceCateName;
    private double defaultRate;
    private int km;
    private double addingRate;

    public VehicleDetailsDTO() {
    }

    public VehicleDetailsDTO(int makeId, String makeName, int modelId, String modelName, int vehicleId, String vehicleNo, int vehicleYear, String colour, int isActive, String isActiveName, int priceCateId, String priceCateName, double defaultRate, int km, double addingRate) {
        this.makeId = makeId;
        this.makeName = makeName;
        this.modelId = modelId;
        this.modelName = modelName;
        this.vehicleId = vehicleId;
        this.vehicleNo = vehicleNo;
        this.vehicleYear = vehicleYear;
        this.colour = colour;
        this.isActive = isActive;
        this.isActiveName = isActiveName;
        this.priceCateId = priceCateId;
        this.priceCateName = priceCateName;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getPriceCateId() {
        return priceCateId;
    }

    public void setPriceCateId(int priceCateId) {
        this.priceCateId = priceCateId;
    }

    public String getPriceCateName() {
        return priceCateName;
    }

    public void setPriceCateName(String priceCateName) {
        this.priceCateName = priceCateName;
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

    public String getIsActiveName() {
        return isActiveName;
    }

    public void setIsActiveName(String isActiveName) {
        this.isActiveName = isActiveName;
    }
}
