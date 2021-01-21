package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ReservationDAO;
import entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean add(Reservation reservation) throws Exception {
        return false;
    }

    @Override
    public Reservation search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(Reservation r) throws Exception {
        String sql = "UPDATE reservation set reservation_status=? WHERE res_id=?";
        return CrudUtil.executeUpdate(sql,r.getResStatus(),r.getResId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        return null;
    }

    @Override
    public int addReservation(Reservation r) throws Exception {
        String sql = "INSERT INTO `reservation` (`cust_id`, `user_id`, `transaction_date`, `reservation_status`) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, r.getCustId(),r.getUserId(),r.getTransactionDate(),r.getResStatus());
    }

    @Override
    public boolean updateStatus(Reservation r) throws Exception {
        String sql = "UPDATE reservation set reservation_status=? WHERE res_id=?";
        return CrudUtil.executeUpdate(sql,r.getResStatus(),r.getResId());
    }
}
