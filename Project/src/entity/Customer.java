package entity;

public class Customer {
    private int custId;
    private String custNIC;
    private String custName;
    private String custAddress;
    private String mobileNo;

    public Customer() {
    }

    public Customer(String custNIC, String custName, String custAddress, String mobileNo) {
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
    }

    public Customer(int custId, String custNIC, String custName, String custAddress, String mobileNo) {
        this.custId = custId;
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustNIC() {
        return custNIC;
    }

    public void setCustNIC(String custNIC) {
        this.custNIC = custNIC;
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
}
