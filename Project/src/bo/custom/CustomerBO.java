package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean addCustomer(CustomerDTO c) throws Exception;
    public boolean updateCustomer(CustomerDTO c) throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception;
    public ArrayList<CustomerDTO> searchCustomers(String value) throws Exception;
}
