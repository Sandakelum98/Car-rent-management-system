package tm;

import javafx.scene.control.Button;

public class CustomerTM {
    private int custID;
    private String custNIC;
    private String custName;
    private String custAddress;
    private String mobileNo;
    private Button btn;

    public CustomerTM() {
    }

    public CustomerTM(String custNIC, String custName, String custAddress, String mobileNo) {
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
    }

    public CustomerTM(int custID, String custNIC, String custName, String custAddress, String mobileNo, Button btn) {
        this.custID = custID;
        this.custNIC = custNIC;
        this.custName = custName;
        this.custAddress = custAddress;
        this.mobileNo = mobileNo;
        this.btn = btn;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
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