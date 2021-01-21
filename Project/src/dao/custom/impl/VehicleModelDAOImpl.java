package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleModelDAO;
import entity.VehicleModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModelDAOImpl implements VehicleModelDAO {
    @Override
    public boolean add(Object o) throws Exception {
        return false;
    }

    @Override
    public Object search(Object o) throws Exception {
        return null;
    }

    @Override
    public boolean update(Object o) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public ArrayList<VehicleModel> getAll() throws Exception {
        String sql = "SELECT * from vehicle_model";
        ArrayList<VehicleModel> allModels = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            allModels.add(new VehicleModel(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3)
            ));
        }
        return allModels;
    }

    @Override
    public ArrayList<VehicleModel> getModels(String make) throws Exception {
        String sql = "SELECT vehicle_model.* FROM vehicle_model , vehicle_make WHERE vehicle_make.make_id = vehicle_model.make_id AND vehicle_make.make_name = ?";
        ArrayList<VehicleModel> vehicleModels = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql,make);
        while (rst.next()){
            vehicleModels.add(new VehicleModel(rst.getInt(1),rst.getInt(2),rst.getString(3)));
        }
        return vehicleModels;
    }
}
