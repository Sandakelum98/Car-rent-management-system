package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleMakeDAO;
import entity.VehicleMake;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VehicleMakeDAOImpl implements VehicleMakeDAO {
    @Override
    public boolean add(VehicleMake vehicleMake) throws Exception {
        String sql = "INSERT INTO vehicle_make values make_name=?";
        return CrudUtil.executeUpdate(sql, vehicleMake.getMakeName());
    }

    @Override
    public VehicleMake search(String makeName) throws Exception {
        String sql = "SELECT * FROM vehicle_make WHERE make_name=?";
        ResultSet rst = CrudUtil.executeQuery(sql,makeName);
        while (rst.next()){
            return new VehicleMake(rst.getInt(1),rst.getString(2));
        }
        return null;
    }

    @Override
    public boolean update(VehicleMake vehicleMake) throws Exception {
        String sql = "UPDATE vehicle_make SET make_name=? WHERE make_id=?";
        return CrudUtil.executeUpdate(sql, vehicleMake.getMakeName(), vehicleMake.getMakeId());
    }

    @Override
    public boolean delete(String makeName) throws Exception {
        String sql = "DELETE FROM vehicle_make WHERE make_name=?";
        return CrudUtil.executeUpdate(sql,makeName);
    }

    @Override
    public ArrayList<VehicleMake> getAll() throws Exception {
        String sql="select * from vehicle_make";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<VehicleMake> vehicleMakes=new ArrayList<>();
        while (rst.next()){
            VehicleMake vehicleMake = new VehicleMake(rst.getInt(1), rst.getString(2));
            vehicleMakes.add(vehicleMake);
        }
        return vehicleMakes;
    }
}
