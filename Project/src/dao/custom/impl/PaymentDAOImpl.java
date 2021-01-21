package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entity.Payment;

import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment payment) throws Exception {
        return false;
    }

    @Override
    public Payment search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        return null;
    }

    @Override
    public int addPayment(Payment p) throws Exception {
        String sql = "INSERT INTO payment (res_id,payment_date,payment_type,amount) values(?,?,?,?)";
        return CrudUtil.execute(sql,p.getResID(), p.getPaymentDate(), p.getPaymentType(), p.getAmount());
    }
}
