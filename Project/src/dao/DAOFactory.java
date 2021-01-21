package dao;

import bo.BOFactory;
import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.PriceCategoryBOImpl;
import dao.custom.impl.*;
import dto.PaymentDetailDTO;
import dto.ReservationDetailDTO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory==null) ? daoFactory=new DAOFactory(): daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,VEHICLEMAKE,USER,PRICECATEGORY,VEHICLEMODEL,VEHICLE,Query,RESERVATION,RESERVATIONDETAIL,RESERVATIONDAYS,
        PAYMENT,PAYMENTDETAIL
    }

    public SuperDAO getDAO(DAOFactory.DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLEMAKE:
                return new VehicleMakeDAOImpl();
            case VEHICLEMODEL:
                return new VehicleModelDAOImpl();
            case USER:
                return new UserDAOImpl();
            case PRICECATEGORY:
                return new PriceCategoryDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case Query:
                return new QueryDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case RESERVATIONDETAIL:
                return new ReservationDetailDAOImpl();
            case RESERVATIONDAYS:
                return new ReservationDaysDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PAYMENTDETAIL:
                return new PaymentDetailDAOImpl();
            default:
                return null;

        }
    }
}
