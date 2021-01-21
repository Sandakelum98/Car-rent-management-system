package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ReservationDaysDAO;
import entity.ReservationDays;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservationDaysDAOImpl implements ReservationDaysDAO {
    @Override
    public boolean add(ReservationDays r) throws Exception {
        String sql = "INSERT INTO reservation_days (reservation_datail_id,reservation_date) values(?,?)";
        return CrudUtil.executeUpdate(sql,r.getResDetailId(),r.getResDate());
    }

    @Override
    public ReservationDays search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(ReservationDays reservationDays) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ReservationDays> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> getDays(String fromDate, String toDate) throws Exception {
        String sql = "CALL `dateList`(?,?)";
        ResultSet rst = CrudUtil.executeQuery(sql,fromDate,toDate);
        ArrayList<String> getDates = new ArrayList<>();
        while (rst.next()){
            getDates.add(rst.getString(1));
        }
        return getDates;
    }

    @Override
    public boolean deleteDays(int resDetailId) throws Exception {
        String sql = "DELETE FROM reservation_days WHERE reservation_datail_id=?";
        return CrudUtil.executeUpdate(sql,resDetailId);
    }
}
