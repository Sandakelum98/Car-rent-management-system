package dao.custom;

import dao.SuperDAO;
import dto.BookingDTO;
import dto.DashBoardDTO;
import dto.ReturnDTO;
import dto.SelectVehicleDTO;
import entity.Issue;
import entity.SelectVehicle;
import entity.VehicleDetails;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<VehicleDetails> getAllVehicleDetails() throws Exception;
    ArrayList<VehicleDetails> searchVehicleDetails(String value) throws Exception;
    ArrayList<SelectVehicle> getAvailableVehicles(SelectVehicleDTO selectVehicleDTO) throws Exception;
    ArrayList<Issue> SearchBooking(String date, String mobileNo) throws Exception;
    ArrayList<ReturnDTO> getIntoForReturn(String value) throws Exception;
    ArrayList<BookingDTO> getAllBookinds() throws Exception;
    ArrayList<BookingDTO> searchBooking(String value) throws Exception;
    ArrayList<DashBoardDTO> loadBarChart(DashBoardDTO dashBoardDTO) throws Exception;
}
