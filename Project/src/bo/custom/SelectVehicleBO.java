package bo.custom;

import bo.SuperBO;
import dto.SelectVehicleDTO;

import java.util.ArrayList;

public interface SelectVehicleBO extends SuperBO {
    ArrayList<SelectVehicleDTO> getAvailableVehicles(SelectVehicleDTO selectVehicleDTO) throws Exception;
}
