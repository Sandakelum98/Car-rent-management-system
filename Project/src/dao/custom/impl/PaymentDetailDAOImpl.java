package dao.custom.impl;

import dao.CrudDAO;
import dao.CrudUtil;
import dao.custom.PaymentDAO;
import dao.custom.PaymentDetailDAO;
import entity.Payment;
import entity.PaymentDetail;

import java.util.ArrayList;

public class PaymentDetailDAOImpl implements PaymentDetailDAO {

    @Override
    public boolean add(PaymentDetail p) throws Exception {
        String sql = "INSERT INTO payment_detail (payment_id,amount,payment_type,card_number) values (?,?,?,?)";
        return CrudUtil.executeUpdate(sql, p.getPaymentId(), p.getAmount(), p.getPaymentType(), p.getCardNumber());
    }

    @Override
    public PaymentDetail search(String s) throws Exception {
        return null;
    }

    @Override
    public boolean update(PaymentDetail paymentDetail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public ArrayList<PaymentDetail> getAll() throws Exception {
        return null;
    }
}
