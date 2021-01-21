package dto;

public class ReturnDTO {
    private String custName;
    private String vehicleModel;
    private int resId;
    private int resDetailId;
    private double defaultRate;
    private int km;
    private double addingRate;
    private String fromDate;
    private String toDate;
    private int startMileage;
    private double advanceAmount;
    private int totalDays;

    private ReservationDTO reservationDTO;
    private ReservationDetailDTO reservationDetailDTO;
    private PaymentDTO paymentDTO;
    private PaymentDetailDTO paymentDetailDTO;

    public ReturnDTO() {
    }

    public ReturnDTO(ReservationDTO reservationDTO, ReservationDetailDTO reservationDetailDTO, PaymentDTO paymentDTO, PaymentDetailDTO paymentDetailDTO) {
        this.reservationDTO = reservationDTO;
        this.reservationDetailDTO = reservationDetailDTO;
        this.paymentDTO = paymentDTO;
        this.paymentDetailDTO = paymentDetailDTO;
    }

    public ReturnDTO(int resDetailId, String fromDate, String toDate) {
        this.resDetailId = resDetailId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ReturnDTO(String custName, String vehicleModel, int resId, int resDetailId, double defaultRate, int km, double addingRate, String fromDate, String toDate, int startMileage, double advanceAmount, int totalDays) {
        this.custName = custName;
        this.vehicleModel = vehicleModel;
        this.resId = resId;
        this.resDetailId = resDetailId;
        this.defaultRate = defaultRate;
        this.km = km;
        this.addingRate = addingRate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.startMileage = startMileage;
        this.advanceAmount = advanceAmount;
        this.totalDays = totalDays;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getResDetailId() {
        return resDetailId;
    }

    public void setResDetailId(int resDetailId) {
        this.resDetailId = resDetailId;
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

    public int getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(int startMileage) {
        this.startMileage = startMileage;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
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
}
