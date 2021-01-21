package bo.custom.impl;

import bo.custom.BookingBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.*;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDETAIL);
    ReservationDaysDAO reservationDaysDAO = (ReservationDaysDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATIONDAYS);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    PaymentDetailDAO paymentDetailDAO = (PaymentDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENTDETAIL);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public int addBooking(BookingDTO bookingDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        ReservationDTO reservationDTO = bookingDTO.getReservationDTO();
        Reservation reservation = new Reservation(
                reservationDTO.getCustId(),
                reservationDTO.getUserId(),
                reservationDTO.getTransactionDate(),
                reservationDTO.getResStatus()
        );
        int resId = reservationDAO.addReservation(reservation);
        try {
            if (resId > 0) {
                ReservationDetailDTO reservationDetailDTO = bookingDTO.getReservationDetailDTO();
                ReservationDetail reservationDetail = new ReservationDetail(
                        resId,
                        reservationDetailDTO.getVehicleId(),
                        reservationDetailDTO.getFromDate(),
                        reservationDetailDTO.getToDate(),
                        reservationDetailDTO.getDefaultRate(),
                        reservationDetailDTO.getKm(),
                        reservationDetailDTO.getAddingRate()
                );
                int resDetailId = reservationDetailDAO.addReservationDetail(reservationDetail);
                if (resDetailId > 0) {
                    System.out.println("A");
                    ArrayList<String> getDays = reservationDaysDAO.getDays(reservationDetailDTO.getFromDate(),reservationDetailDTO.getToDate());
                    boolean isAddedDates=true;
                    for (String date : getDays) {
                        System.out.println("B");
                        ReservationDays reservationDays = new ReservationDays(resDetailId, date);
                        boolean addedDates = reservationDaysDAO.add(reservationDays);
                        if (!addedDates){
                            isAddedDates=false;
                        }
                    }
                    System.out.println("C");
                    if(isAddedDates){
                        PaymentDTO paymentDTO = bookingDTO.getPaymentDTO();
                        Payment payment = new Payment(
                                resId,
                                paymentDTO.getPaymentDate(),
                                paymentDTO.getPaymentType(),
                                paymentDTO.getAmount()
                        );
                        int paymentId = paymentDAO.addPayment(payment);
                        if(paymentId > 0){
                            PaymentDetailDTO paymentDetailDTO = bookingDTO.getPaymentDetailDTO();
                            PaymentDetail paymentDetail = new PaymentDetail(
                                    paymentId,
                                    paymentDetailDTO.getAmount(),
                                    paymentDetailDTO.getPaymentType(),
                                    paymentDetailDTO.getCardNumber()
                            );
                            boolean isAddedPaymentDetail = paymentDetailDAO.add(paymentDetail);
                            if(isAddedPaymentDetail){
                                connection.commit();
                                return resId;
                            }else{
                                connection.rollback();
                                return 0;
                            }
                        }else{
                            connection.rollback();
                            return 0;
                        }
                    }else{
                        connection.rollback();
                        return 0;
                    }
                } else {
                    connection.rollback();
                    return 0;
                }

            } else {
                connection.rollback();
                return 0;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<BookingDTO> getAllBookinds() throws Exception {
        return queryDAO.getAllBookinds();
    }

    @Override
    public ArrayList<BookingDTO> searchBooking(String value) throws Exception {
        return queryDAO.searchBooking(value);
    }


}
