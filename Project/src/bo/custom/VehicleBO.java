package bo.custom;

import bo.SuperBO;
import dto.VehicleDTO;
import dto.VehicleDetailsDTO;
import dto.VehicleMakeDTO;
import dto.VehicleModelDTO;

import java.util.ArrayList;

public interface VehicleBO extends SuperBO {
    boolean addVehicle(VehicleDTO v) throws Exception;
    ArrayList<VehicleDetailsDTO> searchVehicleDetails(String value) throws Exception;
    ArrayList<VehicleMakeDTO> getAllMake() throws Exception;
    ArrayList<VehicleModelDTO> getModels(String make) throws Exception;
    ArrayList<VehicleModelDTO> getAllModels() throws Exception;
    ArrayList<VehicleDetailsDTO> getAllVehicleDetails() throws Exception;
}
