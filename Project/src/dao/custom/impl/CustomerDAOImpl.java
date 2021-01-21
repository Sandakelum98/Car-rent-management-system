package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws Exception {
        String sql = "INSERT INTO customer (nic,cust_name,cust_address,mobile_no) values (?,?,?,?)";
        return CrudUtil.executeUpdate(sql,c.getCustNIC(),c.getCustName(),c.getCustAddress(),c.getMobileNo());
    }

    @Override
    public Customer search(String mobileNo) throws Exception {
        String sql = "SELECT * FROM customer WHERE mobile_no=?";
        ResultSet rst = CrudUtil.executeQuery(sql,mobileNo);
        while (rst.next()){
            return new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String value) throws Exception {
        String sql = "SELECT * FROM customer WHERE customer.nic LIKE '%"+value+"%' OR customer.cust_name LIKE '%"+value+"%' OR customer.cust_address LIKE '%"+value+"%' OR customer.mobile_no LIKE '%"+value+"%'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Customer> searchCustomers = new ArrayList<>();
        while (rst.next()){
            searchCustomers.add(new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5))
            );
        }
        return searchCustomers;
    }

    @Override
    public boolean update(Customer c) throws Exception {
        String sql = "UPDATE customer set nic=?,cust_name=?,cust_address=?,mobile_no=? WHERE cust_id=?";
        return CrudUtil.executeUpdate(sql,c.getCustNIC(),c.getCustName(),c.getCustAddress(), c.getMobileNo(), c.getCustId());
    }

    @Override
    public boolean delete(String mobileNo) throws Exception {
        String sql = "DELETE FROM customer WHERE mobile_no=?";
        return CrudUtil.executeUpdate(sql,mobileNo);
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            allCustomers.add(new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return allCustomers;
    }


}
