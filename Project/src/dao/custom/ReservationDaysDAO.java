package dao.custom;

import dao.CrudDAO;
import entity.ReservationDays;

import java.util.ArrayList;

public interface ReservationDaysDAO extends CrudDAO<ReservationDays,String> {
    public ArrayList<String> getDays(String fromDate, String toDAte) throws Exception;
    boolean deleteDays(int resDetailId) throws Exception;
}
