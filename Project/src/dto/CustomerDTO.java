package dto;

public class CustomerDTO {
    private int custID;
    private String custNIC;
    private String custName;
    private String custAddress;
    private String mobileNo;

    public CustomerDTO() {
    }

    public CustomerDTO(String custNIC, String custName, String custAddress, String mobileNo) {
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
    }

    public CustomerDTO(int custID, String custNIC, String custName, String custAddress, String mobileNo) {
        this.custID = custID;
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
    }


    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
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