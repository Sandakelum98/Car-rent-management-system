package dao.custom;

import dao.CrudDAO;
import entity.VehicleModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleModelDAO extends CrudDAO {
    ArrayList<VehicleModel> getModels(String make) throws Exception;
}
