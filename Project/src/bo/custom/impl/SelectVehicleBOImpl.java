package bo.custom.impl;

import bo.custom.SelectVehicleBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.SelectVehicleDTO;
import entity.SelectVehicle;

import java.util.ArrayList;

public class SelectVehicleBOImpl implements SelectVehicleBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public ArrayList<SelectVehicleDTO> getAvailableVehicles(SelectVehicleDTO selectVehicleDTO) throws Exception {
        ArrayList<SelectVehicle> availableVehicles = queryDAO.getAvailableVehicles(selectVehicleDTO);
        ArrayList<SelectVehicleDTO> availableVehiclesDto = new ArrayList<>();
        for (SelectVehicle v : availableVehicles) {
            availableVehiclesDto.add(new SelectVehicleDTO(
                    v.getVehicleId(),
                    v.getVehicleNo(),
                    v.getVehicleYear(),
                    v.getVehicleColour(),
                    v.getMakeName(),
                    v.getModelName(),
                    v.getDefaultRate(),
                    v.getKm(),
                    v.getAddingRate(),
                    v.getFromDate(),
                    v.getToDate()
            ));

        }
        return availableVehiclesDto;
    }
}
