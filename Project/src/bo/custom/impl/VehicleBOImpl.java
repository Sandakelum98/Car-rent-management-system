package bo.custom.impl;

import bo.custom.VehicleBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.VehicleDAO;
import dao.custom.VehicleMakeDAO;
import dao.custom.VehicleModelDAO;
import dto.VehicleDTO;
import dto.VehicleDetailsDTO;
import dto.VehicleMakeDTO;
import dto.VehicleModelDTO;
import entity.Vehicle;
import entity.VehicleDetails;
import entity.VehicleMake;
import entity.VehicleModel;

import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    VehicleMakeDAO vehicleMakeDAO = (VehicleMakeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLEMAKE);
    VehicleModelDAO vehicleModelDAO = (VehicleModelDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLEMODEL);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public boolean addVehicle(VehicleDTO v) throws Exception {
        Vehicle vehicle = new Vehicle(
                v.getPrice_categoryId(),
                v.getMakeId(),
                v.getModelId(),
                v.getVehicleNo(),
                v.getYear(),
                v.getVehicleColour());
        return vehicleDAO.add(vehicle);
    }

    @Override
    public ArrayList<VehicleDetailsDTO> searchVehicleDetails(String value) throws Exception {
        ArrayList<VehicleDetails> searchvehicleDetails = queryDAO.searchVehicleDetails(value);
        ArrayList<VehicleDetailsDTO> searchvehicleDetailsDTOS = new ArrayList<>();
        for (VehicleDetails v: searchvehicleDetails) {
            searchvehicleDetailsDTOS.add(new VehicleDetailsDTO(
                    v.getMakeId(),
                    v.getMakeName(),
                    v.getModelId(),
                    v.getModelName(),
                    v.getVehicleId(),
                    v.getVehicleNo(),
                    v.getVehicleYear(),
                    v.getColour(),
                    v.getIsActive(),
                    v.getIsActiveName(),
                    v.getPriceCateId(),
                    v.getPriceCateName(),
                    v.getDefaultRate(),
                    v.getKm(),
                    v.getAddingRate()
            ));
        }
        return searchvehicleDetailsDTOS;
    }

    @Override
    public ArrayList<VehicleMakeDTO> getAllMake() throws Exception {
        ArrayList<VehicleMake> all = vehicleMakeDAO.getAll();
        ArrayList<VehicleMakeDTO> vehicleMakeDTOS=new ArrayList<>();
        for (VehicleMake v:all) {
            VehicleMakeDTO vehicleMake = new VehicleMakeDTO(v.getMakeId(), v.getMakeName());
            vehicleMakeDTOS.add(vehicleMake);
        }
        return vehicleMakeDTOS;
    }

    @Override
    public ArrayList<VehicleModelDTO> getModels(String make) throws Exception {
        ArrayList<VehicleModel> vehicleModels = vehicleModelDAO.getModels(make);
        ArrayList<VehicleModelDTO> vehicleModelDto = new ArrayList<>();
        for (VehicleModel v : vehicleModels) {
            vehicleModelDto.add(new VehicleModelDTO(v.getModelId(),v.getMakeId(),v.getModelName()));
        }
        return vehicleModelDto;
    }

    @Override
    public ArrayList<VehicleModelDTO> getAllModels() throws Exception {
        ArrayList<VehicleModel> allModels = vehicleModelDAO.getAll();
        ArrayList<VehicleModelDTO> allModelsDTO = new ArrayList<>();
        for (VehicleModel v : allModels) {
            allModelsDTO.add(new VehicleModelDTO(
                    v.getModelId(),
                    v.getMakeId(),
                    v.getModelName()
            ));
        }
        return allModelsDTO;
    }

    @Override
    public ArrayList<VehicleDetailsDTO> getAllVehicleDetails() throws Exception {
        ArrayList<VehicleDetails> vehicleDetails = queryDAO.getAllVehicleDetails();
        ArrayList<VehicleDetailsDTO> vehicleDetailsDTOS = new ArrayList<>();
        for (VehicleDetails v : vehicleDetails) {
            vehicleDetailsDTOS.add(new VehicleDetailsDTO(
                    v.getMakeId(),
                    v.getMakeName(),
                    v.getModelId(),
                    v.getModelName(),
                    v.getVehicleId(),
                    v.getVehicleNo(),
                    v.getVehicleYear(),
                    v.getColour(),
                    v.getIsActive(),
                    v.getIsActiveName(),
                    v.getPriceCateId(),
                    v.getPriceCateName(),
                    v.getDefaultRate(),
                    v.getKm(),
                    v.getAddingRate()
            ));
        }
        return vehicleDetailsDTOS;
    }
}
