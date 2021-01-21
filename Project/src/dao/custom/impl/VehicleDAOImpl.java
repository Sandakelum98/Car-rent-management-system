package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleDAO;
import entity.Vehicle;

import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean add(Vehicle v) throws Exception {
        String sql = "INSERT into vehicle (price_category_id,make_id,model_id,vehicle_no,year,vehicle_colour) values (?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(
                sql,
                v.getPrice_categoryId(),
                v.getMakeId(),
                v.getModelId(),
                v.getVehicleNo(),
                v.getYear(),
                v.getVehicleColour()
        );
    }

    @Override
    public Vehicle search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(Vehicle vehicle) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws Exception {
        return null;
    }
}
