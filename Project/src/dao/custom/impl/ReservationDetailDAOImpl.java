package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ReservationDetailDAO;
import entity.ReservationDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {
    @Override
    public boolean add(ReservationDetail r) throws Exception {
        return false;
    }

    @Override
    public ReservationDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(ReservationDetail r) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ReservationDetail> getAll() throws Exception {
        return null;
    }

    @Override
    public int addReservationDetail(ReservationDetail r) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `reservation_detail` (`res_id`, `vehicle_id`, `from_date`, `to_date`, `default_rate`, `km`, `adding_rate`) VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(
                sql,
                r.getResId(),
                r.getVehicleId(),
                r.getFromDate(),
                r.getToDate(),
                r.getDefaultRate(),
                r.getKm(),
                r.getAddingRate()
        );
    }

    @Override
    public boolean addStartMileage(ReservationDetail r) throws Exception {
        String sql = "UPDATE reservation_detail SET start_mileage=? WHERE res_detail_id=?";
        return CrudUtil.executeUpdate(sql,r.getEndMileage(),r.getResDetailId());
    }

    @Override
    public boolean updateReturnDate(int resDetailId, String toDate) throws Exception {
        String sql ="Update reservation_detail SET to_date=? WHERE res_detail_id=?";
        return CrudUtil.executeUpdate(sql,toDate,resDetailId);
    }

    @Override
    public boolean addEndMileage(ReservationDetail r) throws Exception {
        String sql = "UPDATE reservation_detail SET end_mileage=? WHERE res_detail_id=?";
        return CrudUtil.executeUpdate(sql, r.getEndMileage(), r.getResDetailId());
    }

    @Override
    public boolean updateTotalAmount(ReservationDetail r) throws Exception {
        String sql = "UPDATE reservation_detail SET total_amount=? WHERE res_detail_id=?";
        return CrudUtil.executeUpdate(sql,r.getTotalAmount(),r.getResDetailId());
    }
}
