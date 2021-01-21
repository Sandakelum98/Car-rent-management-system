package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public ArrayList<Customer> searchCustomers(String value) throws Exception;
}
