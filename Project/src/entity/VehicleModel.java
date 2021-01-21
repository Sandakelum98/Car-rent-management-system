package entity;

public class VehicleModel {
    private int modelId;
    private int makeId;
    private String modelName;

    public VehicleModel() {
    }

    public VehicleModel(int modelId, int makeId, String modelName) {
        this.modelId = modelId;
        this.makeId = makeId;
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
