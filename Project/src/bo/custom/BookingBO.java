package bo.custom;

import bo.SuperBO;
import dto.BookingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingBO extends SuperBO {
    public int addBooking(BookingDTO bookingDTO) throws Exception;
    public ArrayList<BookingDTO> getAllBookinds() throws Exception;
    public ArrayList<BookingDTO> searchBooking(String value) throws Exception;
}
