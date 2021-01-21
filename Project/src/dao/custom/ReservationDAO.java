package dao.custom;

import dao.CrudDAO;
import entity.Reservation;

import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    public int addReservation(Reservation reservation) throws Exception;
    boolean updateStatus(Reservation reservation) throws Exception;
}
