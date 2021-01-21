package bo.custom.impl;

import bo.custom.IssueBO;

import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.ReservationDAO;
import dao.custom.ReservationDetailDAO;
import db.DBConnection;
import dto.IssueDTO;
import entity.Issue;
import entity.Reservation;
import entity.ReservationDetail;

import java.sql.Connection;
import java.util.ArrayList;

public class IssueBOImpl implements IssueBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAIL);

    @Override
    public ArrayList<IssueDTO> searchBooking(String date, String value) throws Exception {
        ArrayList<Issue> searchBooking = queryDAO.SearchBooking(date,value);
        ArrayList<IssueDTO> searchBookingDto = new ArrayList<>();
        for (Issue issue : searchBooking) {
            searchBookingDto.add(new IssueDTO(
                    issue.getCustName(),
                    issue.getCustNIC(),
                    issue.getCustAddress(),
                    issue.getMobileNo(),
                    issue.getVehicleMake(),
                    issue.getGetVehicleModel(),
                    issue.getVehicleNo(),
                    issue.getVehicleYear(),
                    issue.getVehicleColour(),
                    issue.getResId(),
                    issue.getResDetailId(),
                    issue.getDefaultRate(),
                    issue.getKm(),
                    issue.getAddingRate(),
                    issue.getFromDate(),
                    issue.getToDate()
            ));
        }
        return searchBookingDto;
    }

    @Override
    public boolean issueVehicle(IssueDTO issueDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        System.out.println(issueDTO.getStartMileage());

        Reservation reservation = new Reservation(issueDTO.getResId(),issueDTO.getReStatus());
        boolean isResUpdate = reservationDAO.updateStatus(reservation);
        try {
            if (isResUpdate) {
                ReservationDetail reservationDetail = new ReservationDetail(issueDTO.getResDetailId(), issueDTO.getStartMileage());
                boolean isReDeUpdated = reservationDetailDAO.addStartMileage(reservationDetail);
                if (isReDeUpdated) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
