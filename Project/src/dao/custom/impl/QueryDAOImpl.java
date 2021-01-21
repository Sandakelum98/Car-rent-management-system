package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import dto.BookingDTO;
import dto.DashBoardDTO;
import dto.ReturnDTO;
import dto.SelectVehicleDTO;
import entity.Issue;
import entity.SelectVehicle;
import entity.VehicleDetails;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<VehicleDetails> getAllVehicleDetails() throws Exception {
        String sql = "SELECT vehicle_make.make_id,\n" +
                "\tvehicle_make.make_name,\n" +
                "\tvehicle_model.model_id,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tvehicle.vehicle_id,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.year,\n" +
                "\tvehicle.vehicle_colour,\n" +
                "\tvehicle.is_active,\n" +
                "\tif(vehicle.is_active>0,'Active','Inactive') AS is_active_name,\n" +
                "\tprice_category.price_category_id,\n" +
                "\tprice_category.price_category_name,\n" +
                "\tprice_category.default_rate,\n" +
                "\tprice_category.km,\n" +
                "\tprice_category.adding_rate \n" +
                "FROM vehicle,vehicle_make,vehicle_model,price_category\n" +
                "WHERE vehicle.make_id=vehicle_make.make_id AND\n" +
                "\tvehicle.model_id=vehicle_model.model_id and\n" +
                "\tvehicle.price_category_id=price_category.price_category_id\n" +
                "\t";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<VehicleDetails> vehicleDetails = new ArrayList<>();
        while (rst.next()){
            vehicleDetails.add(new VehicleDetails(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getString(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getDouble(13),
                    rst.getInt(14),
                    rst.getDouble(15)
            ));
        }
        return vehicleDetails;
    }

    @Override
    public ArrayList<VehicleDetails> searchVehicleDetails(String value) throws Exception {
        String sql = "SELECT vehicle_make.make_id,\n" +
                "\tvehicle_make.make_name,\n" +
                "\tvehicle_model.model_id,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tvehicle.vehicle_id,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.year,\n" +
                "\tvehicle.vehicle_colour,\n" +
                "\tvehicle.is_active,\n" +
                "\tif(vehicle.is_active>0,'Active','Inactive') AS is_active_name,\n" +
                "\tprice_category.price_category_id,\n" +
                "\tprice_category.price_category_name,\n" +
                "\tprice_category.default_rate,\n" +
                "\tprice_category.km,\n" +
                "\tprice_category.adding_rate \n" +
                "FROM vehicle,vehicle_make,vehicle_model,price_category\n" +
                "WHERE vehicle.make_id=vehicle_make.make_id AND\n" +
                "\tvehicle.model_id=vehicle_model.model_id and\n" +
                "\tvehicle.price_category_id=price_category.price_category_id AND\n" +
                "\t(vehicle_make.make_name LIKE '%"+value+"%' or\n" +
                "\tvehicle_model.model_name LIKE '%"+value+"%' or\n" +
                "\tvehicle.vehicle_no LIKE '%"+value+"%' or\n" +
                "\tvehicle.year LIKE '%"+value+"%' or\n" +
                "\tvehicle.vehicle_colour LIKE '%"+value+"%' or\n" +
                "\tprice_category.default_rate LIKE '%"+value+"%')";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<VehicleDetails> searchVehicleDetails = new ArrayList<>();
        while (rst.next()){
            searchVehicleDetails.add(new VehicleDetails(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getString(10),
                    rst.getInt(11),
                    rst.getString(12),
                    rst.getDouble(13),
                    rst.getInt(14),
                    rst.getDouble(15)
            ));
        }
        return searchVehicleDetails;
    }

    @Override
    public ArrayList<SelectVehicle> getAvailableVehicles(SelectVehicleDTO selectVehicleDTO) throws Exception {
        String fromDate = selectVehicleDTO.getFromDate();
        String toDate = selectVehicleDTO.getToDate();
        String model = selectVehicleDTO.getSelectedModel();

        String sql = "SELECT vehicle.vehicle_id,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.year,\n" +
                "\tvehicle.vehicle_colour,\n" +
                "\tvehicle_make.make_name,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tprice_category.default_rate,\n" +
                "\tprice_category.km,\n" +
                "\tprice_category.adding_rate,\n" +
                "\t'"+fromDate+"' AS from_date,\n" +
                "\t'"+toDate+"' AS to_date,\n" +
                "\t(SELECT temp_res_date.reservation_datail_id \n" +
                "FROM reservation_days temp_res_date\n" +
                "WHERE temp_res_date.reservation_date BETWEEN '"+fromDate+"' AND '"+toDate+"'\n" +
                "AND temp_res_date.reservation_datail_id=reservation_detail.res_detail_id\n" +
                "GROUP BY temp_res_date.reservation_datail_id) AS id\n" +
                "\t\n" +
                "FROM vehicle\n" +
                "INNER JOIN vehicle_make ON vehicle_make.make_id=vehicle.make_id\n" +
                "INNER JOIN vehicle_model ON vehicle_model.model_id=vehicle.model_id\n" +
                "INNER JOIN price_category ON price_category.price_category_id=vehicle.price_category_id\n" +
                "LEFT JOIN reservation_detail ON reservation_detail.vehicle_id=vehicle.vehicle_id\n" +
                "left JOIN reservation_days ON reservation_days.reservation_datail_id=reservation_detail.res_detail_id\n" +
                "LEFT JOIN reservation ON reservation.res_id=reservation_detail.res_id\n" +
                "WHERE (reservation_days.reservation_date NOT BETWEEN '"+fromDate+"' AND '"+toDate+"'\n" +
                " OR reservation_days.reservation_date IS NULL)\n" +
                "AND ( '"+model+"' = '' or vehicle_model.model_name='"+model+"')\n" +
                "GROUP BY vehicle.vehicle_id\n" +
                "HAVING id IS NULL ";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<SelectVehicle> availableVehicles = new ArrayList<>();
        while (rst.next()){
            availableVehicles.add(new SelectVehicle(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getDouble(7),
                    rst.getInt(8),
                    rst.getDouble(9),
                    rst.getString(10),
                    rst.getString(11)
            ));
        }
        return availableVehicles;
    }

    @Override
    public ArrayList<Issue> SearchBooking(String date, String value) throws Exception {
        String sql = "SELECT customer.cust_name,\n" +
                "\tcustomer.nic,\n" +
                "\tcustomer.cust_address,\n" +
                "\tcustomer.mobile_no,\n" +
                "\tvehicle_make.make_name,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.year,\n" +
                "\tvehicle.vehicle_colour,\n" +
                "\treservation.res_id,\n" +
                "\treservation_detail.res_detail_id,\n" +
                "\treservation_detail.default_rate,\n" +
                "\treservation_detail.km,\n" +
                "\treservation_detail.adding_rate,\n" +
                "\treservation_detail.from_date,\n" +
                "\treservation_detail.to_date\n" +
                "FROM customer,vehicle,reservation,reservation_detail,vehicle_make,vehicle_model\n" +
                "WHERE customer.cust_id=reservation.cust_id AND\n" +
                "\tvehicle.vehicle_id=reservation_detail.vehicle_id AND \n" +
                "\treservation.res_id=reservation_detail.res_id AND \n" +
                "\tvehicle_make.make_id=vehicle.make_id AND \n" +
                "\tvehicle_model.model_id=vehicle.model_id AND \n" +
                "\treservation.reservation_status='Booked' AND \n" +
                "\treservation_detail.from_date='"+date+"' AND \n" +
                "\t( customer.mobile_no='"+value+"' OR customer.nic='"+value+"' OR vehicle.vehicle_no='"+value+"') limit 1";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Issue> searchBooking = new ArrayList<>();
        while(rst.next()){
            searchBooking.add(new Issue(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getInt(8),
                    rst.getString(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getDouble(12),
                    rst.getInt(13),
                    rst.getDouble(14),
                    rst.getString(15),
                    rst.getString(16)
            ));
        }
        return searchBooking;
    }

    @Override
    public ArrayList<ReturnDTO> getIntoForReturn(String value) throws Exception {
        String sql = "SELECT customer.cust_name,\n" +
                "\tvehicle_model.model_name,\n" +
                "\treservation.res_id,\n" +
                "\treservation_detail.res_detail_id,\n" +
                "\treservation_detail.default_rate,\n" +
                "\treservation_detail.km,\n" +
                "\treservation_detail.adding_rate,\n" +
                "\treservation_detail.from_date,\n" +
                "\treservation_detail.to_date,\n" +
                "\treservation_detail.start_mileage,\n" +
                "\tpayment.amount,\n" +
                "\tDATEDIFF(reservation_detail.to_date,reservation_detail.from_date)+1 AS days\n" +
                "FROM customer,vehicle,reservation,reservation_detail,vehicle_make,vehicle_model,payment\n" +
                "WHERE customer.cust_id=reservation.cust_id AND\n" +
                "\tvehicle.vehicle_id=reservation_detail.vehicle_id AND \n" +
                "\treservation.res_id=reservation_detail.res_id AND \n" +
                "\tvehicle_make.make_id=vehicle.make_id AND \n" +
                "\tvehicle_model.model_id=vehicle.model_id AND \n" +
                "\tpayment.res_id=reservation.res_id AND \n" +
                "\treservation.reservation_status='Issued' AND \n" +
                "\t( customer.mobile_no='"+value+"' OR customer.nic='"+value+"' OR vehicle.vehicle_no='"+value+"')";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ReturnDTO> returnDTOS = new ArrayList<>();
        while (rst.next()){
            returnDTOS.add(new ReturnDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getDouble(5),
                    rst.getInt(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getInt(10),
                    rst.getDouble(11),
                    rst.getInt(12)
            ));
        }
        return returnDTOS;
    }

    @Override
    public ArrayList<BookingDTO> getAllBookinds() throws Exception {
        String sql = "SELECT customer.cust_id,\n" +
                "\tcustomer.nic,\n" +
                "\tcustomer.cust_name,\n" +
                "\tcustomer.cust_address,\n" +
                "\tcustomer.mobile_no,\n" +
                "\treservation.res_id,\n" +
                "\treservation.transaction_date,\n" +
                "\treservation_detail.res_detail_id,\n" +
                "\treservation_detail.from_date,\n" +
                "\treservation_detail.to_date,\n" +
                "\treservation_detail.default_rate,\n" +
                "\treservation_detail.km,\n" +
                "\treservation_detail.adding_rate,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.model_id,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tpayment.payment_id,\n" +
                "\tpayment.amount,\n" +
                "\tpayment_detail.payment_detail_id,\n" +
                "\tpayment_detail.payment_type,\n" +
                "\tpayment_detail.card_number\n" +
                "FROM customer,reservation,reservation_detail,payment,payment_detail,vehicle,vehicle_model,vehicle_make\n" +
                "WHERE reservation.cust_id=customer.cust_id AND \n" +
                "\treservation_detail.res_id=reservation.res_id AND \n" +
                "\treservation_detail.vehicle_id=vehicle.vehicle_id AND \n" +
                "\treservation_detail.vehicle_id=vehicle.vehicle_id AND \n" +
                "\treservation.reservation_status='Booked' AND\n" +
                "\tvehicle.make_id=vehicle_make.make_id AND\n" +
                "\tvehicle.model_id=vehicle_model.model_id AND \n" +
                "\tpayment.res_id=reservation.res_id AND \n" +
                "\tpayment_detail.payment_id=payment.payment_id\n" +
                "GROUP BY reservation.res_id";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BookingDTO> allBookings = new ArrayList<>();
        while (rst.next()){
            allBookings.add(new BookingDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getInt(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getInt(12),
                    rst.getDouble(13),
                    rst.getString(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getInt(17),
                    rst.getDouble(18),
                    rst.getInt(19),
                    rst.getString(20),
                    rst.getInt(21)
            ));
        }
        return allBookings;
    }

    @Override
    public ArrayList<BookingDTO> searchBooking(String value) throws Exception {
        String sql = "SELECT customer.cust_id,\n" +
                "\tcustomer.nic,\n" +
                "\tcustomer.cust_name,\n" +
                "\tcustomer.cust_address,\n" +
                "\tcustomer.mobile_no,\n" +
                "\treservation.res_id,\n" +
                "\treservation.transaction_date,\n" +
                "\treservation_detail.res_detail_id,\n" +
                "\treservation_detail.from_date,\n" +
                "\treservation_detail.to_date,\n" +
                "\treservation_detail.default_rate,\n" +
                "\treservation_detail.km,\n" +
                "\treservation_detail.adding_rate,\n" +
                "\tvehicle.vehicle_no,\n" +
                "\tvehicle.model_id,\n" +
                "\tvehicle_model.model_name,\n" +
                "\tpayment.payment_id,\n" +
                "\tpayment.amount,\n" +
                "\tpayment_detail.payment_detail_id,\n" +
                "\tpayment_detail.payment_type,\n" +
                "\tpayment_detail.card_number\n" +
                "FROM customer,reservation,reservation_detail,payment,payment_detail,vehicle,vehicle_model,vehicle_make\n" +
                "WHERE reservation.cust_id=customer.cust_id AND \n" +
                "\treservation_detail.res_id=reservation.res_id AND \n" +
                "\treservation_detail.vehicle_id=vehicle.vehicle_id AND \n" +
                "\treservation_detail.vehicle_id=vehicle.vehicle_id AND \n" +
                "\treservation.reservation_status='Booked' AND\n" +
                "\tvehicle.make_id=vehicle_make.make_id AND\n" +
                "\tvehicle.model_id=vehicle_model.model_id AND \n" +
                "\tpayment.res_id=reservation.res_id AND \n" +
                "\tpayment_detail.payment_id=payment.payment_id AND\n" +
                "\t(customer.nic LIKE '%"+value+"%' OR  \n" +
                "\tcustomer.cust_name LIKE '%"+value+"%' OR \n" +
                "\tcustomer.mobile_no LIKE '%"+value+"%' OR \n" +
                "\tcustomer.cust_address LIKE '%"+value+"%' OR \n" +
                "\tvehicle_model.model_name LIKE '%"+value+"%' or\n" +
                "\tvehicle.vehicle_no LIKE '%"+value+"%')\n" +
                "GROUP BY reservation.res_id";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BookingDTO> searchBooking = new ArrayList<>();
        while (rst.next()){
            searchBooking.add(new BookingDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getInt(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getDouble(11),
                    rst.getInt(12),
                    rst.getDouble(13),
                    rst.getString(14),
                    rst.getInt(15),
                    rst.getString(16),
                    rst.getInt(17),
                    rst.getDouble(18),
                    rst.getInt(19),
                    rst.getString(20),
                    rst.getInt(21)
            ));
        }
        return searchBooking;
    }

    @Override
    public ArrayList<DashBoardDTO> loadBarChart(DashBoardDTO dashBoardDTO) throws Exception {
        String sql = "SELECT reservation_detail.from_date AS issue_date,count(reservation_detail.res_detail_id) AS count\n" +
                "FROM reservation_detail\n" +
                "WHERE reservation_detail.from_date BETWEEN '"+dashBoardDTO.getDate()+"' AND DATE_ADD(\"2020-10-04\", INTERVAL '"+dashBoardDTO.getNoOfDays()+"' DAY)\n" +
                "GROUP BY reservation_detail.from_date";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<DashBoardDTO> dashBoardDTOS = new ArrayList<>();
        while (rst.next()){
            dashBoardDTOS.add(new DashBoardDTO(rst.getString(1),rst.getInt(2)));
        }
        return dashBoardDTOS;
    }
}
