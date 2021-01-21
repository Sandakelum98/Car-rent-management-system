package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO c) throws Exception {
        Customer customer = new Customer(c.getCustNIC(),c.getCustName(),c.getCustAddress(),c.getMobileNo());
        return customerDAO.add(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO c) throws Exception {
        Customer customer = new Customer(c.getCustID(), c.getCustNIC(), c.getCustName(), c.getCustAddress(), c.getMobileNo());
        return customerDAO.update(customer);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> allCustomers = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomersDTO = new ArrayList<>();
        for (Customer c : allCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(c.getCustId(),c.getCustNIC(),c.getCustName(),c.getCustAddress(),c.getMobileNo());
            allCustomersDTO.add(customerDTO);
        }
        return allCustomersDTO;
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomers(String value) throws Exception {
        ArrayList<Customer> searchCustomers = customerDAO.searchCustomers(value);
        ArrayList<CustomerDTO> searchCustomersDTO = new ArrayList<>();
        for (Customer c : searchCustomers){
            searchCustomersDTO.add(new CustomerDTO(c.getCustId(),c.getCustNIC(),c.getCustName(),c.getCustAddress(),c.getMobileNo()));
        }
        return searchCustomersDTO;
    }

}
