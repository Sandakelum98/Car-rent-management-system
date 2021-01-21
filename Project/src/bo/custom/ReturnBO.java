package bo.custom;

import bo.SuperBO;
import dto.ReturnDTO;

import java.util.ArrayList;

public interface ReturnBO extends SuperBO {
    public ArrayList<ReturnDTO> searchBooking(String value) throws Exception;
    boolean editReturnDate (ReturnDTO returnDTO) throws Exception;
    public boolean saveEndMileage(int resDetailId, int endMileage) throws Exception;
    boolean returnVehicle(ReturnDTO returnDTO) throws Exception;
}
