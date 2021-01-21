package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;

public class CustomerUpdateFormController {
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNo;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;

    CustomerDTO customerDTO;
    CustomerFormController customerFormController;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    //focus
    public void nicTextAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void nameTextAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void addressTextAction(ActionEvent actionEvent) {
        txtMobileNo.requestFocus();
    }

    public void mobileNoTextAction(ActionEvent actionEvent) {
        updateButtonAction(actionEvent);
    }


    //Update Button Actions
    public void updateButtonAction(ActionEvent actionEvent) {

        if(txtNIC.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter NIC !").show();
            txtNIC.setFocusColor(Paint.valueOf("red"));
            txtNIC.requestFocus();
            return;
        }
        if (txtName.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Name !").show();
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
            return;
        }
        if(txtMobileNo.getText().length()==0){
            new Alert(Alert.AlertType.WARNING,"Please enter Mobile No !").show();
            txtMobileNo.setFocusColor(Paint.valueOf("red"));
            txtMobileNo.requestFocus();
            return;
        }

        int custId = customerDTO.getCustID();
        String custNIC = txtNIC.getText();
        String custName = txtName.getText();
        String custAddress = txtAddress.getText();
        String custMobileNo = txtMobileNo.getText();

        CustomerDTO customerDTO = new CustomerDTO(custId, custNIC, custName, custAddress, custMobileNo);

        try {
            boolean isAdded = customerBO.updateCustomer(customerDTO);
            if(isAdded){
                new Alert(Alert.AlertType.WARNING,"Updated").show();
                customerFormController.loadAllCustomers();
                Stage stage = (Stage) btnUpdate.getScene().getWindow();
                stage.close();
            }else{
                new Alert(Alert.AlertType.WARNING,"Update Failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateBtnEnter(MouseEvent mouseEvent) {
    }

    public void updateBtnExit(MouseEvent mouseEvent) {
    }


    //Cancel Button Action
    public void cancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void cancelBtnEnter(MouseEvent mouseEvent) {
    }

    public void cancelBtnExit(MouseEvent mouseEvent) {
    }


    //Methods
    public void updateCustomer(CustomerDTO c,CustomerFormController controller){
        this.customerDTO=c;
        this.customerFormController=controller;

        txtNIC.setText(c.getCustNIC());
        txtName.setText(c.getCustName());
        txtAddress.setText(c.getCustAddress());
        txtMobileNo.setText(c.getMobileNo());
    }
}
