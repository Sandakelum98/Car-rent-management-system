package dto;

public class PriceCategoryDTO {
    private int priceCategoryId;
    private String priceCategoryName;
    private double defaultRate;
    private int km;
    private double addingRate;

    public PriceCategoryDTO() {
    }

    public PriceCategoryDTO(String priceCategoryName, double defaultRate, int km, double addingRate) {
        this.setPriceCategoryName(priceCategoryName);
        this.setDefaultRate(defaultRate);
        this.setKm(km);
        this.setAddingRate(addingRate);
    }

    public PriceCategoryDTO(int priceCategoryId, String priceCategoryName, double defaultRate, int km, double addingRate) {
        this.setPriceCategoryId(priceCategoryId);
        this.setPriceCategoryName(priceCategoryName);
        this.setDefaultRate(defaultRate);
        this.setKm(km);
        this.setAddingRate(addingRate);
    }

    public String getPriceCategoryName() {
        return priceCategoryName;
    }

    public void setPriceCategoryName(String priceCategoryName) {
        this.priceCategoryName = priceCategoryName;
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

    public int getPriceCategoryId() {
        return priceCategoryId;
    }

    public void setPriceCategoryId(int priceCategoryId) {
        this.priceCategoryId = priceCategoryId;
    }
}
