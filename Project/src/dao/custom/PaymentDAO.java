package dao.custom;

import dao.CrudDAO;
import entity.Payment;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    public int addPayment(Payment payment) throws Exception;
}
