package dao.custom;

import dao.CrudDAO;
import entity.ReservationDetail;

import java.sql.SQLException;

public interface ReservationDetailDAO extends CrudDAO<ReservationDetail,String> {
    public int addReservationDetail(ReservationDetail reservationDetail) throws SQLException, ClassNotFoundException;
    public boolean addStartMileage(ReservationDetail r) throws Exception;
    public boolean updateReturnDate(int resDetailId, String toDate) throws Exception;
    public boolean addEndMileage(ReservationDetail r) throws Exception;
    public boolean updateTotalAmount(ReservationDetail r) throws Exception;
}
