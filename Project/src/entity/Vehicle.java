package entity;

public class Vehicle {
    private int vehicleId;
    private int price_categoryId;
    private int makeId;
    private int modelId;
    private String vehicleNo;
    private int year;
    private String vehicleColour;
    private int isActive;

    public Vehicle() {
    }

    public Vehicle(int price_categoryId, int makeId, int modelId, String vehicleNo, int year, String vehicleColour) {
        this.price_categoryId = price_categoryId;
        this.makeId = makeId;
        this.modelId = modelId;
        this.vehicleNo = vehicleNo;
        this.year = year;
        this.vehicleColour = vehicleColour;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getPrice_categoryId() {
        return price_categoryId;
    }

    public void setPrice_categoryId(int price_categoryId) {
        this.price_categoryId = price_categoryId;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
