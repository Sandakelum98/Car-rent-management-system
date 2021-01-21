package dto;

import dto.PaymentDTO;
import dto.PaymentDetailDTO;
import dto.ReservationDTO;
import dto.ReservationDetailDTO;

public class BookingDTO {
    private ReservationDTO reservationDTO;
    private ReservationDetailDTO reservationDetailDTO;
    private PaymentDTO paymentDTO;
    private PaymentDetailDTO paymentDetailDTO;

    private int custId;
    private String custNic;
    private String custName;
    private String custAddress;
    private String mobileNo;
    private int resId;
    private String transactionDate;
    private int resDetailId;
    private String fromDate;
    private String toDate;
    private double defaultRate;
    private int km;
    private double addingRate;
    private String vehicleNo;
    private int modelId;
    private String modelName;
    private int paymentId;
    private double amount;
    private int paymentDetailId;
    private String payment_type;
    private int cardNumber;

    public BookingDTO() {
    }

    public BookingDTO(ReservationDTO reservationDTO, ReservationDetailDTO reservationDetailDTO, PaymentDTO paymentDTO, PaymentDetailDTO paymentDetailDTO) {
        this.reservationDTO = reservationDTO;
        this.reservationDetailDTO = reservationDetailDTO;
        this.paymentDTO = paymentDTO;
        this.paymentDetailDTO = paymentDetailDTO;
    }

    public BookingDTO(int custId, String custNic, String custName, String custAddress, String mobileNo, int resId, String transactionDate, int resDetailId, String fromDate, String toDate, double defaultRate, int km, double addingRate, String vehicleNo, int modelId, String modelName, int paymentId, double amount, int paymentDetailId, String payment_type, int cardNumber) {
        this.custId = custId;
        this.custNic = custNic;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
        this.resId = resId;
        this.transactionDate = transactionDate;
        this.resDetailId = resDetailId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
        this.vehicleNo = vehicleNo;
        this.modelId = modelId;
        this.modelName = modelName;
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDetailId = paymentDetailId;
        this.payment_type = payment_type;
        this.cardNumber = cardNumber;
    }

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    public void setReservationDTO(ReservationDTO reservationDTO) {
        this.reservationDTO = reservationDTO;
    }

    public ReservationDetailDTO getReservationDetailDTO() {
        return reservationDetailDTO;
    }

    public void setReservationDetailDTO(ReservationDetailDTO reservationDetailDTO) {
        this.reservationDetailDTO = reservationDetailDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public PaymentDetailDTO getPaymentDetailDTO() {
        return paymentDetailDTO;
    }

    public void setPaymentDetailDTO(PaymentDetailDTO paymentDetailDTO) {
        this.paymentDetailDTO = paymentDetailDTO;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustNic() {
        return custNic;
    }

    public void setCustNic(String custNic) {
        this.custNic = custNic;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getResDetailId() {
        return resDetailId;
    }

    public void setResDetailId(int resDetailId) {
        this.resDetailId = resDetailId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(double defaultRate) {
        this.defaultRate = defaultRate;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public double getAddingRate() {
        return addingRate;
    }

    public void setAddingRate(double addingRate) {
        this.addingRate = addingRate;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentDetailId() {
        return paymentDetailId;
    }

    public void setPaymentDetailId(int paymentDetailId) {
        this.paymentDetailId = paymentDetailId;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
