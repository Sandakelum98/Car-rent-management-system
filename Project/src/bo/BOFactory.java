package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory() {
        return (boFactory==null) ? boFactory=new BOFactory(): boFactory;
    }

    public enum BOTypes {
        CUSTOMER,VEHICLE,USER,PRICECATEGORY,SELECTVEHICLE,BOOKING,ISSUE,RETURN,DASHBOARD
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case USER:
                return new UserBOImpl();
            case PRICECATEGORY:
                return new PriceCategoryBOImpl();
            case SELECTVEHICLE:
                return new SelectVehicleBOImpl();
            case BOOKING:
                return new BookingBOImpl();
            case ISSUE:
                return new IssueBOImpl();
            case RETURN:
                return new ReturnBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            default:
                return null;

        }
    }
}
