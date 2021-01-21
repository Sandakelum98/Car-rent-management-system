package entity;

public class VehicleMake {
    private int makeId;
    private String makeName;

    public VehicleMake() {
    }

    public VehicleMake(String makeName) {
        this.makeName = makeName;
    }

    public VehicleMake(int makeId, String makeName) {
        this.makeId = makeId;
        this.makeName = makeName;
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
}
