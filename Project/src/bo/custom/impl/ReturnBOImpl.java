package bo.custom.impl;

import bo.custom.ReturnBO;
import dao.DAOFactory;
import dao.custom.*;
import dao.custom.impl.ReservationDaysDAOImpl;
import db.DBConnection;
import dto.*;
import entity.*;

import java.sql.Connection;
import java.util.ArrayList;

public class ReturnBOImpl implements ReturnBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAIL);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    PaymentDetailDAO paymentDetailDAO = (PaymentDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENTDETAIL);
    ReservationDaysDAO reservationDaysDAO = (ReservationDaysDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDAYS);

    @Override
    public ArrayList<ReturnDTO> searchBooking(String value) throws Exception {
        ArrayList<ReturnDTO> returnDTOS = queryDAO.getIntoForReturn(value);
        return returnDTOS;
    }

    @Override
    public boolean editReturnDate(ReturnDTO returnDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        int resDetailId = returnDTO.getResDetailId();
        boolean isDeleted = reservationDaysDAO.deleteDays(resDetailId);
        boolean isAddedDates=true;
        try {
            if (isDeleted) {
                System.out.println("Delete");
                boolean isUpdateToDate = reservationDetailDAO.updateReturnDate(resDetailId,returnDTO.getToDate());
                if(isUpdateToDate) {
                    ArrayList<String> getDays = reservationDaysDAO.getDays(returnDTO.getFromDate(), returnDTO.getToDate());
                    for (String date : getDays) {
                        ReservationDays reservationDays = new ReservationDays(resDetailId, date);
                        isAddedDates = reservationDaysDAO.add(reservationDays);
                    }
                    if (isAddedDates) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                }else{
                    connection.rollback();
                    return false;
                }
            } else {
                System.out.println("not Delete");
                connection.rollback();
                return false;
            }
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean saveEndMileage(int resDetailId, int endMileage) throws Exception {
        ReservationDetail reservationDetail = new ReservationDetail(resDetailId, endMileage);
        return reservationDetailDAO.addEndMileage(reservationDetail);
    }

    @Override
    public boolean returnVehicle(ReturnDTO returnDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        ReservationDTO reservationDTO = returnDTO.getReservationDTO();
        Reservation reservation = new Reservation(reservationDTO.getResId(), reservationDTO.getResStatus());

        boolean isUpdateRes = reservationDAO.updateStatus(reservation);
        try {
            if (isUpdateRes) {
                ReservationDetailDTO reservationDetailDTO = returnDTO.getReservationDetailDTO();
                ReservationDetail reservationDetail = new ReservationDetail(reservationDetailDTO.getResDetailId(), reservationDetailDTO.getTotalAmount());
                boolean isUpdateResDetail = reservationDetailDAO.updateTotalAmount(reservationDetail);
                if (isUpdateResDetail) {
                    PaymentDTO paymentDTO = returnDTO.getPaymentDTO();
                    Payment payment = new Payment(
                            paymentDTO.getResID(),
                            paymentDTO.getPaymentDate(),
                            paymentDTO.getPaymentType(),
                            paymentDTO.getAmount()
                    );
                    int paymentId = paymentDAO.addPayment(payment);
                    if (paymentId > 0) {
                        PaymentDetailDTO paymentDetailDTO = returnDTO.getPaymentDetailDTO();
                        PaymentDetail paymentDetail = new PaymentDetail(
                                paymentId,
                                paymentDetailDTO.getAmount(),
                                paymentDetailDTO.getPaymentType(),
                                paymentDetailDTO.getCardNumber()
                        );
                        boolean isAddPaymentDetail = paymentDetailDAO.add(paymentDetail);
                        if (isAddPaymentDetail) {
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
