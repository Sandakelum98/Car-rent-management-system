package bo.custom;

import bo.SuperBO;
import dto.IssueDTO;
import entity.Issue;

import java.util.ArrayList;

public interface IssueBO extends SuperBO {
 public ArrayList<IssueDTO> searchBooking(String date, String mobileNo) throws Exception;
 public boolean issueVehicle (IssueDTO issueDTO) throws Exception;
}
